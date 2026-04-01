package a.entity.gus06.data.viewer.urlclassloader.form;

import a.framework.*;
import javax.swing.JComponent;
import java.net.URLClassLoader;
import java.net.URL;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140809";}


	private Service formPanel;
 	private URLClassLoader data;


	public EntityImpl() throws Exception
	{formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.panel1");}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (URLClassLoader) obj;
		if(data==null) resetGui();
		else updateGui();
	}
		
		
		
		
		
	private void resetGui() throws Exception
	{
		formPanel.e();
	}
    
    
	
	private void updateGui() throws Exception
	{
		formPanel.e();
		formPanel.v("Jar URL 0",jarURL0());
		formPanel.v("Jar URL nb",jarURLNb());
	}
    
	
	private String jarURL0()
	{
		URL[] urls = data.getURLs();
		if(urls==null) return "null";
		return toString(urls[0]);
	}
    
	
	private String jarURLNb()
	{
		URL[] urls = data.getURLs();
		if(urls==null) return "0";
		return ""+urls.length;
	}
	
	
	
		
	
	private String toString(Object obj)
	{
		if(obj==null) return "null";
		String s = obj.toString();
		if(s.length()<200) return s;
		return s.substring(0,200)+" ...";
	}
}
