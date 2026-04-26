package a.entity.gus06.dir.explorer.resource.prop;

import a.framework.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.util.Map;
import java.util.Vector;
import java.util.Collections;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, ListSelectionListener {

	public String creationDate() {return "20140902";}

	public static final String ICONID = "FILE_prop";

	private Service buildJList;
	private Service dirToMap;
	private Service editor;
	private Service splitCust;
	private Service clearCPC;
	
	private Service create;
	private Service remove;
	private Service rename;
	private Service duplicate;
	private Service paste;
	private Service copy;

	private JSplitPane split;
	private JList list;
	private JLabel label;
	
	private File dir;
	private Map map;

	
	
	public EntityImpl() throws Exception
	{
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		dirToMap = Outside.service(this,"gus06.dir.children.dirtomap.name_file");
		editor = Outside.service(this,"*gus06.file.editor.ext.properties");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		clearCPC = Outside.service(this,"gus.x.swing.comp.action.clear.copypastecut");
		
		create = Outside.service(this,"gus06.file.perform.create.ask");
		remove = Outside.service(this,"gus06.file.perform.remove.ask");
		rename = Outside.service(this,"gus06.file.perform.rename.ask");
		duplicate = Outside.service(this,"gus06.file.perform.duplicate.ask");
		paste = Outside.service(this,"gus06.dir.perform.copyfiles.fromclipboard");
		copy = Outside.service(this,"gus.y.clipboard1.files");
	
		list = (JList) buildJList.t(ICONID);
		clearCPC.p(list);
			
		list.addListSelectionListener(this);
		
		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if((e.getModifiers() & KeyEvent.CTRL_MASK)!=0)
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_C:copy();break;
						case KeyEvent.VK_V:paste();break;
					}
				}
				else
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_F5:refresh();break;
						case KeyEvent.VK_F1:create();break;
						case KeyEvent.VK_F2:rename();break;
						case KeyEvent.VK_F3:duplicate();break;
						case KeyEvent.VK_DELETE:remove();break;
					}
				}
			}
		});
		
		label = new JLabel(" ");
		
		JPanel panel_left = new JPanel(new BorderLayout());
		panel_left.add(new JScrollPane(list),BorderLayout.CENTER);
		panel_left.add(label,BorderLayout.SOUTH);
	
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(panel_left);
		split.setRightComponent((JComponent) editor.i());
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		if(dir==null) resetGui();
		else updateGui();
	}
	
	
	
	private void refresh()
	{
		try
		{
			if(dir==null) resetGui();
			else updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	private void create()
	{
		try
		{
			if(dir==null) return;
			boolean done = create.f(dir);
			if(done) updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"create()",e);}
	}
	
	
	
	
	private void rename()
	{
		try
		{
			if(dir==null) return;
			File f = selectedFile();
			boolean done = rename.f(f);
			if(done) updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}
	
	
	
	
	private void duplicate()
	{
		try
		{
			if(dir==null) return;
			File f = selectedFile();
			boolean done = duplicate.f(f);
			if(done) updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"duplicate()",e);}
	}
	
	
	
	
	private void remove()
	{
		try
		{
			if(dir==null) return;
			File f = selectedFile();
			boolean done = remove.f(f);
			if(done) updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"remove()",e);}
	}
	
	
	
	
	private void copy()
	{
		try
		{
			if(dir==null) return;
			File f = selectedFile();
			copy.p(f);
		}
		catch(Exception e)
		{Outside.err(this,"copy()",e);}
	}
	
	
	
	
	private void paste()
	{
		try
		{
			if(dir==null) return;
			boolean done = paste.f(dir);
			if(done) updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"paste()",e);}
	}
	
	
	
	
	
	
	
	
	
	private void resetGui()
	{
		try
		{
			map = null;
			list.setListData(new Vector());
			label.setText(" ");
			editor.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"resetGui()",e);}	
	}
	
	
	
	private void updateGui()
	{
		try
		{
			map = (Map) dirToMap.t(dir);
			list.setListData(buildKeys());
			label.setText(" Number: "+mapSize());
			editor.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private Vector buildKeys()
	{
		if(map==null) return new Vector();
		Vector keys = new Vector(map.keySet());
		Collections.sort(keys);
		return keys;
	}
	
	
	
	private int mapSize()
	{
		if(map==null) return -1;
		return map.size();
	}
	
	


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty())
			{editor.p(null);return;}

			File f = selectedFile();
			editor.p(f);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	private File selectedFile()
	{
		String id = (String) list.getSelectedValue();
		return (File) map.get(id);
	}
}
