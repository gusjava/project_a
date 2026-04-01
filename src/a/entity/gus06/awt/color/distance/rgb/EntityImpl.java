package a.entity.gus06.awt.color.distance.rgb;

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
		int r = c1.getRed()-c2.getRed();
		int g = c1.getGreen()-c2.getGreen();
		int b = c1.getBlue()-c2.getBlue();
		
		return r*r + g*g + b*b;
	}
}
