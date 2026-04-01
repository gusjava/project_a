package a.entity.gus06.find.float1.ashuecolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}
	
	private Service intArrayToColor;

	public EntityImpl() throws Exception
	{
		intArrayToColor = Outside.service(this,"gus06.convert.intarraytocolor");
	}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Float) return obj;
		if(obj instanceof Number) return Float.valueOf(((Number) obj).floatValue());
		if(obj instanceof String) return Float.valueOf((String) obj);
		if(obj instanceof Color) return findHue((Color) obj);
		if(obj instanceof int[]) return findHue((Color) intArrayToColor.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private float findHue(Color c)
	{
		float[] hsb = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),null);
		return Float.valueOf(hsb[0]);
	}
}
