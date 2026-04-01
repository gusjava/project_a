package a.entity.gus06.data.viewer.jcomponent;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140731";}


	private Service guiDisplay;
	private Service guiChildren;
	
	private JComponent data;
	private JTabbedPane tabbedPane;

	
	public EntityImpl() throws Exception
	{
		guiDisplay = Outside.service(this,"*gus06.data.viewer.jcomponent.display");
		guiChildren = Outside.service(this,"*gus06.data.viewer.jcomponent.children");
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Display",(JComponent) guiDisplay.i());
		tabbedPane.addTab("Children",(JComponent) guiChildren.i());
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (JComponent) obj;
		guiDisplay.p(data);
		guiChildren.p(data);
	}
}
