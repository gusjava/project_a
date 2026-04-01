package a.entity.gus06.sys.filemanagement1.gui.gui1_2.explorer;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I, P, ItemListener {

	public String creationDate() {return "20191107";}

	public static final String ICONID_COMBO1 = "UTIL_disk";
	public static final String ICONID_COMBO2 = "time";


	private Service findNames;
	private Service findTimeStamps;
	private Service custCombo;
	private Service gui1;
	
	private JPanel panel;
	private JComboBox combo1;
	private JComboBox combo2;
	
	private Object engine;
	
	private String[] names;
	private List timeStamps;
	
	

	public EntityImpl() throws Exception
	{
		findNames = Outside.service(this,"gus06.dir.listing0.files.names0");
		findTimeStamps = Outside.service(this,"gus06.sys.filemanagement1.scan.timestamps.find");
		custCombo = Outside.service(this,"gus06.swing.combobox.cust2.renderer.icon");
		gui1 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_2.explorer.gui1");
		
		combo1 = new JComboBox();
		combo2 = new JComboBox();
		
		combo1.addItemListener(this);
		combo2.addItemListener(this);
		
		custCombo.v(ICONID_COMBO1,combo1);
		custCombo.v(ICONID_COMBO2,combo2);
		
		JPanel panel_top = new JPanel(new GridLayout(1,2));
		panel_top.add(combo1);
		panel_top.add(combo2);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel_top,BorderLayout.NORTH);
		panel.add((JComponent) gui1.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(engine!=null) ((S)engine).removeActionListener(this);
		engine = obj;
		if(engine!=null) ((S)engine).addActionListener(this);
		
		if(engine==null) {reset();return;}
		reload();
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("scanCompleted()")) reload();
		if(s.equals("rootChanged()")) reload();
	}
	
	
	
	private void reload()
	{
		try
		{
			if(engine==null) return;
			
			File dirRoots = (File) ((R)engine).r("dirRoots");
			names = (String[]) findNames.t(dirRoots);
			
			combo1.removeAllItems();
			if(names!=null)
			{
				for(String name : names) combo1.addItem(name);
				selectionChanged1();
			}
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	
	private void reset()
	{
		names = null;
		timeStamps = null;
		combo1.removeAllItems();
	}
	
	
	public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getSource();
		if(source==combo1) selectionChanged1();
		if(source==combo2) selectionChanged2();
	}
	
	
	
	private void selectionChanged1()
	{
		try
		{
			int index = combo1.getSelectedIndex();
			if(index<0 || names==null)
			{
				combo2.removeAllItems();
				timeStamps = null;
				gui1.p(null);
			}
			else
			{
				String name = names[index];
				
				File dir0 = (File) ((R) engine).r("dirScans");
				File dir1 = new File(dir0,name);
				timeStamps = (List) findTimeStamps.t(dir1);
				
				combo2.removeAllItems();
				for(Object timeStamp : timeStamps) 
				combo2.addItem((String) timeStamp);
				selectionChanged2();
			}
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged1()",e);}
	}
	
	
	
	private void selectionChanged2()
	{
		try
		{
			int index1 = combo1.getSelectedIndex();
			int index2 = combo2.getSelectedIndex();
			if(index1<0 || index2<0)
			{
				gui1.p(null);
			}
			else
			{
				String name = names[index1];
				String timeStamp = (String) timeStamps.get(index2);
				
				gui1.p(new Object[]{engine,name,timeStamp});
			}
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged2()",e);}
	}
}