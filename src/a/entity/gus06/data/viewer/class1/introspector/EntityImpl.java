package a.entity.gus06.data.viewer.class1.introspector;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140819";}


	private Service selector;
	private Service viewer;
	
	private JSplitPane split;
	
 	private Class data;



	
	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.data.viewer.class1.introspector.selector");
		viewer = Outside.service(this,"*gus06.data.viewer.class1.introspector.objviewer");
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) selector.i());
		split.setRightComponent((JComponent) viewer.i());

		selector.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (Class) obj;
		selector.p(data);
		viewer.p(null);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	
	private void selected()
	{
		try
		{
			Object element = selector.g();
			viewer.p(element);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}
