package a.entity.gus06.file.editor.ext.svg;

import a.framework.*;
import java.io.File;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20250424";}

	private Service gui1;
	private Service gui2;
	
	private JTabbedPane tab;
	private File file;
	
	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.file.editor.ext.svg.display");
		gui2 = Outside.service(this,"*gus06.file.editor.ext.svg.content");
		
		tab = new JTabbedPane();
		tab.addTab("Display",(JComponent) gui1.i());
		tab.addTab("Content",(JComponent) gui2.i());
	}
	
	public Object i() throws Exception
	{return tab;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		gui1.p(file);
		gui2.p(file);
	}
}