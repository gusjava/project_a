package a.entity.gus06.data.viewer.urlclassloader;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.net.URLClassLoader;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140809";}

	private Service form;
	private Service loader;

	private JTabbedPane tabbedPane;
	
	private URLClassLoader data;
	
	
	
	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.data.viewer.urlclassloader.form");
		loader = Outside.service(this,"*gus06.data.viewer.urlclassloader.loader");
		
		tabbedPane = new JTabbedPane();
		addTab("Infos",form);
		addTab("Loader",loader);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (URLClassLoader) obj;
		form.p(data);
		loader.p(data);
	}
	
	
	private void addTab(String title, Service s) throws Exception
	{tabbedPane.addTab(title,(JComponent) s.i());}
}
