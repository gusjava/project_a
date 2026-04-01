package a.entity.gus06.sys.filemovedown1.gui1;

import a.framework.*;
import javax.swing.JPanel;
import java.io.File;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;

public class EntityImpl extends S1 implements Entity, I, P, R {

	public String creationDate() {return "20240203";}
	
	public static final Font FONT = new Font(Font.DIALOG, Font.PLAIN, 20);

	private Service findDirs;
	private Service findFiles;
	private Service splitCust;
	private Service fieldHolder;
	private Service previewPanel;
	private Service shiftPanel;
	private Service moveOp;
	private Service dragFrame;

	private JPanel panel;
	private JSplitPane split;
	private JButton buttonClose;
	private JCheckBox checkBoxNext;
	private JTextField fieldComp;
	private JLabel labelLeft;
	
	private File file;
	private File[] files;
	private File parent;
	
	private Map map;
	private List names;
	

	public EntityImpl() throws Exception
	{
		findDirs = Outside.service(this,"gus06.dir.listing0.dirs");
		findFiles = Outside.service(this,"gus06.dir.listing0.files");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		previewPanel = Outside.service(this,"*gus06.sys.filemovedown1.gui1.preview");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		moveOp = Outside.service(this,"gus06.file.op.move.autorename");
		dragFrame = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		
		fieldComp = (JTextField) fieldHolder.i();
		JComponent previewComp = (JComponent) previewPanel.i();
		
		buttonClose = new JButton("Close");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{close();}
		});
		
		checkBoxNext = new JCheckBox("Repeat process with next file");
		checkBoxNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{updateLabelLeft();}
		});
		
		labelLeft = new JLabel(" ");
		
		JPanel choosePanel = new JPanel(new BorderLayout());
		choosePanel.add(fieldComp, BorderLayout.NORTH);
		choosePanel.add((JComponent) shiftPanel.i(), BorderLayout.CENTER);
		
		
		split = new JSplitPane();
		splitCust.p(split);
		split.setDividerLocation(600);
		split.setLeftComponent(previewComp);
		split.setRightComponent(choosePanel);
		
		JPanel bottom = new JPanel(new BorderLayout());
		bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		bottom.add(buttonClose, BorderLayout.WEST);
		bottom.add(labelLeft, BorderLayout.CENTER);
		bottom.add(checkBoxNext, BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(split,BorderLayout.CENTER);
		panel.add(bottom,BorderLayout.SOUTH);
		fieldComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{moveToNew();}
		});
		
		buttonClose.setEnabled(false);
		checkBoxNext.setEnabled(false);
		
		fieldComp.setFont(FONT);
		buttonClose.setFont(FONT);
		checkBoxNext.setFont(FONT);
		
		dragFrame.p(panel);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("fieldComp")) return fieldComp;
		if(key.equals("keys")) return new String[]{"fieldComp"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			resetGui();
			return;
		}
		File f = (File) obj;
		if(f.isFile()) initAsFile(f);
		else if(f.isDirectory()) initAsDir(f);
		else if(!f.exists()) resetGui();
	}
	
	private void initAsFile(File f) throws Exception
	{
		file = f;
		parent = file.getParentFile();
		files = (File[]) findFiles.t(parent);
		init();
	}
	
	private void initAsDir(File f) throws Exception
	{
		parent = f;
		files = (File[]) findFiles.t(parent);
		if(files.length==0) {resetGui(); return;}
		
		file = files[0];
		init();
	}
	
	private void init() throws Exception
	{
		previewPanel.p(file);
		rebuildButtons();
		buttonClose.setEnabled(true);
		checkBoxNext.setEnabled(true);
		updateLabelLeft();
	}
	
	private void resetGui() throws Exception
	{
		file = null;
		parent = null;
		previewPanel.p(null);
		shiftPanel.p(null);
		map = new HashMap();
		names = new ArrayList();
		buttonClose.setEnabled(true);
		checkBoxNext.setEnabled(false);
	}
	
	
	private void rebuildButtons() throws Exception
	{
		File[] dirs = (File[]) findDirs.t(parent);
		map = new HashMap();
		for(int i=0;i<dirs.length;i++)
		{
			File dir = dirs[i];
			String name = dir.getName();
			map.put(name, dir);
		}
		names = new ArrayList(map.keySet());
		Collections.sort(names);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(0,1));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		for(int i=0;i<names.size();i++)
		{
			String name = (String) names.get(i);
			JButton1 button = new JButton1(name);
			buttonsPanel.add(button);
		}
		shiftPanel.p(buttonsPanel);
	}
	
	private void updateLabelLeft()
	{
		if(checkBoxNext.isSelected()) 
			labelLeft.setText(" Files left: "+files.length);
		else labelLeft.setText(" ");
	}
	
	
	
	private void moveToNew()
	{
		try
		{
			String newTag = fieldComp.getText();
			if(newTag==null || newTag.trim().equals("")) return;
			fieldComp.setText("");
			
			if(names.contains(newTag)) moveTo(newTag);
			else
			{
				File newDir = new File(parent, newTag);
				newDir.mkdir();
				rebuildButtons();
				moveTo(newTag);
			}
		}
		catch(Exception e)
		{Outside.err(this,"moveToNew()",e);}
	}
	
	private void moveTo(String name)
	{
		try
		{
			File dir = (File) map.get(name);
			File file1 = new File(dir, file.getName());
			moveOp.p(new File[]{file,file1});
			
			if(checkBoxNext.isSelected()) handleNext();
			else close();
		}
		catch(Exception e)
		{Outside.err(this,"moveTo(String)",e);}
	}
	
	private void handleNext() throws Exception
	{initAsDir(parent);}
	
	
	private void close()
	{send(this,"close()");}
	
	
	private class JButton1 extends JButton implements ActionListener
	{
		private String name;
		public JButton1(String name)
		{
			super(name);
			this.name = name;
			setToolTipText(name);
			setFont(FONT);
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent e)
		{moveTo(name);}
	}
}
