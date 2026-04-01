package a.entity.gus06.sys.expression1.apply.op._iso3166_countryflag_icon;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250503";}


	private Service provider;
	
	public EntityImpl() throws Exception
	{
		provider = Outside.service(this,"gus06.icon.provider.flag.country");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return icon((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Icon icon(String code) throws Exception
	{return (Icon) provider.t(code);}
}