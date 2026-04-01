package a.entity.gus06.icon.loader;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T, R, F {

	public String creationDate() {return "20160914";}

	private Service iconInside;
	private Service loaderOutside;

	public EntityImpl() throws Exception
	{
		iconInside = Outside.service(this,"gus06.app.inside.icon");
		loaderOutside = Outside.service(this,"gus06.icon.loader.outside");
	}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	
	
	public Object r(String key) throws Exception
	{
		Icon icon1 = (Icon) loaderOutside.t(key);
		if(icon1!=null) return icon1;
		return (Icon) iconInside.t(key);
	}
	
	
	public boolean f(Object obj) throws Exception
	{return iconInside.f(obj) || loaderOutside.f(obj);}
}