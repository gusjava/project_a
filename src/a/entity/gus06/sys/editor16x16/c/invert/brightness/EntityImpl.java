package a.entity.gus06.sys.editor16x16.c.invert.brightness;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250312";}


	private Service decode;
	private Service encode;

	public EntityImpl() throws Exception
	{
		decode = Outside.service(this,"gus06.sys.editor16x16.c.decode2");
		encode = Outside.service(this,"gus06.sys.editor16x16.c.encode2");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		int[] v = (int[]) decode.t(obj);
		float[] hsb = Color.RGBtoHSB(v[0],v[1],v[2], null);
		hsb[2] = 1.0f - hsb[2];
		
		int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		
		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8)  & 0xFF;
		int b = rgb         & 0xFF;
		int a = v[3];
		
		return encode.t(new int[]{r,g,b,a});
	}
}