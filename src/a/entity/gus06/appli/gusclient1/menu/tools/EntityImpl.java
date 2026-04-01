package a.entity.gus06.appli.gusclient1.menu.tools;

import a.framework.*;

import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140813";}


	public static final String KEY1 = "gusclient1_menu_tools";
	public static final String KEY2 = "gusclient1_tool_";


	private Service toolManager;
	private Service toolList;
	private Service localize;
	


	private JMenu menu;

	
	public EntityImpl() throws Exception
	{
		toolManager = Outside.service(this,"gus06.appli.gusclient1.tool.manager");
		toolList = Outside.service(this,"gus06.appli.gusclient1.tool.list");
		localize = Outside.service(this,"gus06.ling.localize.manager");
		
		List l = (List) toolList.g();
		String id0 = (String) toolManager.g();
		
		menu = new JMenu("Tool");
		localize.v(KEY1,menu);
			
		ButtonGroup group = new ButtonGroup();
		
		for(int i=0;i<l.size();i++)
		{
			String id = (String) l.get(i);
			boolean selected = id.equals(id0);
			
			JRadioButtonMenuItem1 item = new JRadioButtonMenuItem1(id,selected);
			localize.v(KEY2+id,item);
			
			group.add(item);
			menu.add(item);
		}
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	private void changeTool(String id)
	{
		try{toolManager.p(id);}
		catch(Exception e)
		{Outside.err(this,"changeTool(String)",e);}
	}
	
	
	private void updateItem(JRadioButtonMenuItem item, String id)
	{
		try
		{
			if(toolManager.g().equals(id))
			item.setSelected(true);
		}
		catch(Exception e)
		{Outside.err(this,"updateItem(JRadioButtonMenuItem,String)",e);}
	}
	
	
	
	
	
	private class JRadioButtonMenuItem1 extends JRadioButtonMenuItem implements ChangeListener, ActionListener
	{
		private String id;
		public JRadioButtonMenuItem1(String id, boolean selected) throws Exception
		{
			super(id,selected);
			this.id = id;
		
			addChangeListener(this);
			toolManager.addActionListener(this);
		}
		
		public void stateChanged(ChangeEvent e)
		{if(isSelected()) changeTool(id);}
		
		public void actionPerformed(ActionEvent e)
		{updateItem(this,id);}
	}
}
