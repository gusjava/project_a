package a.entity.gus06.sys.expression1.apply.op._rgb;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160830";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.convert.colortointarray.rgb");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Color) return perform.t(obj);
		if(obj instanceof float[]) return hsbToRgb((float[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int[] hsbToRgb(float[] hsb) throws Exception
	{
		if(hsb.length!=3) throw new Exception("Wrong data number: "+hsb.length);
		int rgb = Color.HSBtoRGB(hsb[0],hsb[1],hsb[2]);
		Color c = new Color(rgb);
		return new int[]{c.getRed(),c.getGreen(),c.getBlue()};
	}
}
