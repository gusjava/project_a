package a.entity.gus06.data.viewer.jcomponent.children;

import a.framework.*;
import javax.swing.*;
import java.awt.Component;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140808";}


	private JSplitPane split;
	private JList list;

	private JComponent data;
	private Component[] children;
	

	public EntityImpl() throws Exception
	{
		list = new JList();
	
		split = new JSplitPane();
		split.setLeftComponent(new JScrollPane(list));
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (JComponent) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	
	
	private void resetGui()
	{
		children = null;
		list.setListData(new Object[]{});
	}
	
	private void updateGui()
	{
		children = data.getComponents();
		list.setListData(children);
	}	
}
