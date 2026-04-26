package a.entity.gus06.io.printstream.holder.area;

import a.framework.*;
import java.io.PrintStream;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, G, I, E {

	public String creationDate() {return "20200131";}


	private Service factory;
	private Service build;
	private Service clear;
	
	private JTextArea area;
	private JScrollPane scroll;
	private PrintStream p;
	
	
	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textarea.factory.console1.black.white");
		build = Outside.service(this,"gus06.io.printstream.textarea1");
		clear = Outside.service(this,"gus.x.swing.textcomp.cust.action.escap.clear");
		
		area = (JTextArea) factory.i();
		scroll = new JScrollPane(area);
		
		p = (PrintStream) build.t(area);
		clear.p(area);
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	public Object g() throws Exception
	{return p;}
	
	
	public void e() throws Exception
	{area.setText("");}
}
