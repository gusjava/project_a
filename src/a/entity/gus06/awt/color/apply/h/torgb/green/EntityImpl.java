package a.entity.gus06.awt.color.apply.h.torgb.green;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180411";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Color c = (Color) o[0];
		H h = (H) o[1];
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		int g1 = (int) h.h((double) g);
		if(g1<0) g1 = 0; else if(g1>255) g1 = 255;
		
		return new Color(r,g1,b);
	}
}
