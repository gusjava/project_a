package a.entity.gus06.sys.editor16x16.hue.transform;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250313";}


	private Service decode;
	private Service encode;

	public EntityImpl() throws Exception
	{
		decode = Outside.service(this,"gus06.sys.editor16x16.c.decode2");
		encode = Outside.service(this,"gus06.sys.editor16x16.c.encode2");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new T1((Color) obj);}
	
	
	private class T1 implements T
	{
		private float hue;
		private float saturation;
		
		public T1(Color c)
		{
			float[] hsb = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(), null);
			hue = hsb[0];
			saturation = hsb[1];
		}
	
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return null;
		
			int[] v = (int[]) decode.t(obj);
			
			float[] hsb = Color.RGBtoHSB(v[0],v[1],v[2], null);
			hsb[0] = hue;
			hsb[1] = saturation;
			int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
			
			int r = (rgb >> 16) & 0xFF;
			int g = (rgb >> 8)  & 0xFF;
			int b = rgb         & 0xFF;
			int a = v[3];
			
			return encode.t(new int[]{r,g,b,a});
		}
	}
}