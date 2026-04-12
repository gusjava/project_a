package a.entity.gus06.y.entityeditor1.gui1.src.many;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ListSelectionListener, ActionListener {

	public String creationDate() {return "20251115";}

	public static final String MAIN_NAME = "EntityImpl";

	public static final String DISPLAY_ADD = "FILE_java_add#Add file [F1]";
	public static final String DISPLAY_DELETE = "FILE_java_remove#Delete file [DEL]";
	public static final String DISPLAY_RENAME = "FILE_java_rename#Rename file [F2]";
	public static final String DISPLAY_DUPLICATE = "FILE_java_duplicate#Duplicate file [F3]";

	public static final Color COLOR_SELECT = new Color(244, 244, 244);
	public static final Color COLOR_UNSELECT = Color.WHITE;

	public static final String COL_FILE_NAME = "file_name";

	private Service actionBuilder;
	private Service toolbarFactory;
	private Service getName0;
	private Service javaEditor;

	private Service performAdd;
	private Service performDelete;
	private Service performRename;
	private Service performDuplicate;

	private Action actionAdd;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;
	private Action actionSave;
	private Action actionReload;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	private JToolBar bar;

	private Object data;
	private String entityName;
	private File[] javaFiles;
	private Icon javaIcon;

	private Map map;

	public EntityImpl() throws Exception
	{
		actionBuilder = Outside.service(this, "gus06.y.swing1.action.builder1");
		toolbarFactory = Outside.service(this, "gus06.x.swing.toolbar.factory1");
		getName0 = Outside.service(this, "gus06.file.getname0");
		javaEditor = Outside.service(this, "*gus06.y.entityeditor1.gui1.src.java");

		performAdd = Outside.service(this, "gus06.y.entityeditor1.perform.file.add.ask");
		performDelete = Outside.service(this, "gus06.y.entityeditor1.perform.file.delete.ask");
		performRename = Outside.service(this, "gus06.y.entityeditor1.perform.file.rename.ask");
		performDuplicate = Outside.service(this, "gus06.y.entityeditor1.perform.file.duplicate.ask");

		javaIcon = (Icon) Outside.resource(this, "icon#FILE_java");
		
		actionAdd = (Action) actionBuilder.t(new Object[] { DISPLAY_ADD, (E) this::addFile });
		actionDelete = (Action) actionBuilder.t(new Object[] { DISPLAY_DELETE, (E) this::deleteFile });
		actionRename = (Action) actionBuilder.t(new Object[] { DISPLAY_RENAME, (E) this::renameFile });
		actionDuplicate = (Action) actionBuilder.t(new Object[] { DISPLAY_DUPLICATE, (E) this::duplicateFile });
		actionSave = (Action) javaEditor.r("actionSave");
		actionReload = (Action) javaEditor.r("actionReload");

		labelNumber = new JLabel(" ");
		bar = (JToolBar) toolbarFactory.i();

		bar.add(actionSave);
		bar.add(actionReload);
		bar.addSeparator();
		bar.add(actionAdd);
		bar.add(actionDelete);
		bar.add(actionRename);
		bar.add(actionDuplicate);

		list = new JList();
		list.setCellRenderer(new ListRenderer1());
		list.addListSelectionListener(this);
		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (e.isControlDown())
				{
					if (code == KeyEvent.VK_C) copyFiles();
					else if (code == KeyEvent.VK_V) pasteFiles();
				}
				else
				{
					if (code == KeyEvent.VK_DELETE) deleteFile();
					else if (code == KeyEvent.VK_F1) addFile();
					else if (code == KeyEvent.VK_F2) renameFile();
					else if (code == KeyEvent.VK_F3) duplicateFile();
					else if (code == KeyEvent.VK_F5) reload();
				}
			}
		});

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(labelNumber, BorderLayout.CENTER);
		bottomPanel.add(bar, BorderLayout.EAST);

		JPanel panelLeft = new JPanel(new BorderLayout());
		panelLeft.add(new JScrollPane(list), BorderLayout.CENTER);
		panelLeft.add(bottomPanel, BorderLayout.SOUTH);

		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(150);

		split.setLeftComponent(panelLeft);
		split.setRightComponent((JComponent) javaEditor.i());

		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);

		refreshActions();
	}

	public void p(Object obj) throws Exception
	{
		if (data != null) ((S) data).removeActionListener(this);
		if (obj == null) {reset();return;}
		data = obj;

		entityName = (String) ((R) data).r("entityName");
		javaFiles = (File[]) ((R) data).r("javaFiles");

		reload_();
		((S) data).addActionListener(this);
	}

	private void reset() throws Exception
	{
		data = null;
		entityName = null;
		javaFiles = null;
		map = null;

		list.setListData(new Vector());
		labelNumber.setText(" ");
		javaEditor.p(null);
		refreshActions();
	}

	private void reload()
	{
		try {reload_();}
		catch (Exception e)
		{Outside.err(this, "reload()", e);}
	}

	private void reload_() throws Exception
	{
		map = new HashMap();
		int nb = javaFiles.length;
		for (int i = 0; i < nb; i++)
		{
			String fileName0 = (String) getName0.t(javaFiles[i]);
			map.put(fileName0, javaFiles[i]);
		}

		Vector vec = new Vector(map.keySet());
		if (!vec.contains(MAIN_NAME)) throw new Exception("EntityImpl class not found");
		vec.remove(MAIN_NAME);
		Collections.sort(vec);
		vec.add(0, MAIN_NAME);

		list.setListData(vec);
		labelNumber.setText(" " + vec.size());

		list.setSelectedIndex(0);
		refreshActions();
	}

	public Object i() throws Exception
	{return panel;}

	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}

	private void selectionChanged()
	{
		try
		{
			if (list.isSelectionEmpty())
			{
				javaEditor.p(null);
				refreshActions();
				return;
			}

			File javaFile = (File) map.get(getSelection());
			javaEditor.p(new Object[] { data, javaFile });
			refreshActions();
		}
		catch (Exception e)
		{Outside.err(this, "selectionChanged()", e);}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("srcModified()"))
		{srcModified();return;}
	}

	private void srcModified()
	{list.repaint();}

	private String getSelection()
	{return (String) list.getSelectedValue();}

	private boolean hasSelection()
	{return !list.isSelectionEmpty();}

	private boolean hasSelectionOther()
	{return hasSelection() && !getSelection().equals(MAIN_NAME);}

	private boolean canModifyEntity() throws Exception
	{return permission("canModifyEntity");}

	private boolean permission(String permission) throws Exception
	{return data != null && ((F) data).f(new Object[] { permission, entityName });}

	private void refreshActions() throws Exception
	{
		boolean hasSelectionOther = hasSelectionOther();
		boolean canModify = canModifyEntity();

		actionAdd.setEnabled(canModify);
		actionDelete.setEnabled(canModify && hasSelectionOther);
		actionRename.setEnabled(canModify && hasSelectionOther);
		actionDuplicate.setEnabled(canModify && hasSelectionOther);
	}

	/*
	 * ACTIONS GLOBALES
	 */

	private void addFile()
	{
		try
		{
			if (!canModifyEntity()) return;
			performAdd.p(new Object[] { data, entityName, list });
		}
		catch (Exception e)
		{Outside.err(this, "addFile()", e);}
	}

	private void deleteFile()
	{
		try
		{
			if (!canModifyEntity()) return;
			if (!hasSelectionOther()) return;
			performDelete.p(new Object[] { data, entityName, getSelection(), list });
		}
		catch (Exception e)
		{Outside.err(this, "deleteFile()", e);}
	}

	private void renameFile()
	{
		try
		{
			if (!canModifyEntity()) return;
			if (!hasSelectionOther()) return;
			performRename.p(new Object[] { data, entityName, getSelection(), list });
		}
		catch (Exception e)
		{Outside.err(this, "renameFile()", e);}
	}

	private void duplicateFile()
	{
		try
		{
			if (!canModifyEntity()) return;
			if (!hasSelectionOther()) return;
			performDuplicate.p(new Object[] { data, entityName, getSelection(), list });
		}
		catch (Exception e)
		{Outside.err(this, "duplicateFile()", e);}
	}

	private void copyFiles()
	{
		try
		{

		}
		catch (Exception e)
		{Outside.err(this, "copyFiles()", e);}
	}

	private void pasteFiles() {
		try
		{
			if (!canModifyEntity()) return;
		}
		catch (Exception e)
		{Outside.err(this, "pasteFiles()", e);}
	}
	
	private int getErrorNumber(String fileName0)
	{
		try {
			File javaFile = (File) map.get(fileName0);
			String fileName = javaFile.getName();
			
			List errors = (List) ((R) data).r("compileErrList");
			int nb = 0;
			for (int i = 0; i < errors.size(); i++)
			{
				Map err = (Map) errors.get(i);
				String errFileName = (String) err.get(COL_FILE_NAME);
				if (errFileName.equals(fileName)) nb++;
			}
			return nb;
		}
		catch(Exception e)
		{Outside.err(this, "getErrorNumber(String)", e);}
		return 0;
	}
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer 
	{
		private Font font_p;
		private Font font_i;

		public ListRenderer1()
		{
			super();
			setOpaque(true);
			font_p = getFont().deriveFont(Font.PLAIN);
			font_i = getFont().deriveFont(Font.ITALIC);

			setBackground(COLOR_UNSELECT);
			setFont(font_p);
			setIcon(javaIcon);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			String fileName0 = (String) value;
			int errNumber = getErrorNumber(fileName0);
			
			setText(getText(fileName0, errNumber));
			setFont(getFont(fileName0));
			setBackground(getBackground(isSelected));
			setForeground(getForeground(errNumber));
			
			return this;
		}

		private String getText(String fileName0, int errNumber)
		{
			String s = fileName0 != null ? fileName0 : "null";
			return errNumber>0 ? s + " ("+errNumber+")" : s;
		}

		private Font getFont(String fileName0)
		{return fileName0 != null ? font_p : font_i;}

		private Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
		
		private Color getForeground(int errNumber)
		{return errNumber>0 ? Color.RED : Color.BLACK;}
	}
}