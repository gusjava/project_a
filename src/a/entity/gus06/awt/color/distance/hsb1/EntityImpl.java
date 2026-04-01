package a.entity.gus06.awt.color.distance.hsb1;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}
	
	
	public Object t(Object obj) throws Exception
	{
		Color[] c = (Color[]) obj;
		if(c.length!=2) throw new Exception("Wrong data number: "+c.length);
		return Double.valueOf(distance(c[0],c[1]));
	}
	
	private double distance(Color c1, Color c2)
	{
		float[] hsb1 = Color.RGBtoHSB(c1.getRed(),c1.getGreen(),c1.getBlue(),null);
		float[] hsb2 = Color.RGBtoHSB(c2.getRed(),c2.getGreen(),c2.getBlue(),null);
		
		double h = hsb1[0]-hsb2[0];
		double s = hsb1[1]-hsb2[1];
		double b = hsb1[2]-hsb2[2];
		
		return h*h + 5*s*s + 10*b*b;
	}
}
