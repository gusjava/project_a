package a.entity.gus06.app.inside.icon;

import java.net.URL;

import javax.swing.ImageIcon;

import a.framework.*;

public class EntityImpl implements Entity, T, R, F {

	public String creationDate() {return "20140719";}


	private Service inside;
	
	public EntityImpl() throws Exception
	{inside = Outside.service(this,"inside");}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		URL url = findURL((String) obj);
		return url!=null;
	}
	
	public Object r(String key) throws Exception
	{
		URL url = findURL(key);
		return url!=null ? new ImageIcon(url) : null;
	}
	
	
	private URL findURL(String key) throws Exception
	{
		String path = "icon/"+key+".gif";
		return (URL) inside.t("url."+path);
	}
}