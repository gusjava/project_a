package a.entity.gus06.find.insets;

import a.framework.*;
import java.awt.Insets;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}


	private Service stringToInsets;
	private Service intArrayToInsets;

	public EntityImpl() throws Exception
	{
		stringToInsets = Outside.service(this,"gus06.convert.stringtoinsets");
		intArrayToInsets = Outside.service(this,"gus06.convert.intarraytoinsets");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Insets) return obj;
		
		if(obj instanceof Integer) return integerToInsets((Integer) obj);
		if(obj instanceof String) return stringToInsets.t(obj);
		if(obj instanceof int[]) return intArrayToInsets.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Insets integerToInsets(Integer n)
	{
		int v = n.intValue();
		return new Insets(v,v,v,v);
	}
}
