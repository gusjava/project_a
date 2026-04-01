package a.entity.gus06.dir.explorer.resource.ling;

import a.framework.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Map;
import java.util.Vector;
import java.util.Collections;


public class EntityImpl implements Entity, I, P, ListSelectionListener {

	public String creationDate() {return "20140811";}

	public static final String ICONID = "FILE_ling";

	private Service dirToMap;
	private Service buildJList;
	private Service dicoPanel;
	private Service splitCust;

	private JSplitPane split;
	private JList list;
	private JLabel label;
	
	private File dir;
	private Map map;


	
	public EntityImpl() throws Exception
	{
		dirToMap = Outside.service(this,"gus06.ling.gui.lingdir.dirtomap");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		dicoPanel = Outside.service(this,"*gus06.dir.explorer.resource.ling.dicopanel");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
	
		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);
		
		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{if(e.getKeyCode()==KeyEvent.VK_F5) refresh();}
		});
		
		label = new JLabel(" ");
		
		JPanel panel_left = new JPanel(new BorderLayout());
		panel_left.add(new JScrollPane(list),BorderLayout.CENTER);
		panel_left.add(label,BorderLayout.SOUTH);
	
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(panel_left);
		split.setRightComponent((JComponent) dicoPanel.i());
		split.setDividerLocation(100);
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
	
	
	
	private void resetGui()
	{
		try
		{
			map = null;
			list.setListData(new Vector());
			label.setText(" ");
			dicoPanel.p(null);
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
			dicoPanel.p(null);
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
		if(map==null) return 0;
		return map.size();
	}
	
	


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty())
			{dicoPanel.p(null);return;}

			String id = (String) list.getSelectedValue();
			Map m = (Map) map.get(id);
			dicoPanel.p(m);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
