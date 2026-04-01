package a.entity.gus06.sys.filemanagement1.scan.builder.buildconsole;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.PrintStream;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201012";}


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
	{return build((String) obj);}
	
	
	private Holder build(String title) throws Exception
	{
		Holder holder = new Holder();
		show.v(title,holder.i());
		return holder;
	}
	
	
	private class Holder implements R, I
	{
		private PrintStream p;
		private JTextArea area;
		private JLabel label;
		private JPanel panel;
		
		public Holder() throws Exception
		{
			area = (JTextArea) factory.i();
			label = new JLabel(" ");
			p = (PrintStream) build.t(area);
		
			panel = new JPanel(new BorderLayout());
			panel.add(new JScrollPane(area),BorderLayout.CENTER);
			panel.add(label,BorderLayout.SOUTH);
		}
		
		
		public Object i() throws Exception
		{return panel;}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("printstream")) return p;
			if(key.equals("area")) return area;
			if(key.equals("label")) return label;
			
			if(key.equals("keys")) return new String[]{"printstream","area","label"};
			throw new Exception("Unknown key: "+key);
		}
	}
}
