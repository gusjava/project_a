package a.entity.gus06.find.color;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140729";}


	private Service stringToColor;
	private Service intArrayToColor;
	private Service floatArrayToColor;

	public EntityImpl() throws Exception
	{
		stringToColor = Outside.service(this,"gus06.convert.stringtocolor");
		intArrayToColor = Outside.service(this,"gus06.convert.intarraytocolor");
		floatArrayToColor = Outside.service(this,"gus06.convert.floatarraytocolor");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Color) return obj;
		if(obj instanceof Integer) return integerToColor((Integer) obj);
		if(obj instanceof String) return stringToColor.t(obj);
		if(obj instanceof int[]) return intArrayToColor.t(obj);
		if(obj instanceof float[]) return floatArrayToColor.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
		
	private Color integerToColor(Integer n)
	{return new Color(n.intValue());}
}
