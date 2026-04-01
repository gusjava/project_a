package a.entity.gus06.io.printstream.show.area;

import a.framework.*;
import javax.swing.JTextArea;
import java.io.PrintStream;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191128";}

	
	private Service factory;
	private Service build;
	private Service show;
		
	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textarea.factory.console1.black.white");
		build = Outside.service(this,"gus06.io.printstream.textarea1");
		show = Outside.service(this,"gus06.swing.frame.show");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return build((String) obj);
	}
	
	private PrintStream build(String title) throws Exception
	{
		JTextArea area = (JTextArea) factory.i();
		PrintStream p = (PrintStream) build.t(area);
		show.v(title,new JScrollPane(area));
		return p;
	}
}
