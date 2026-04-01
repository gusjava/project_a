package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.color;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180411";}




	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Color color = (Color) oo[0];
		
		int nb = buildInt(oo);
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		int r1 = r+nb;
		int g1 = g+nb;
		int b1 = b+nb;
		
		if(r1<0) r1=0; else if(r1>255) r1=255;
		if(g1<0) g1=0; else if(g1>255) g1=255;
		if(b1<0) b1=0; else if(b1>255) b1=255;
		
		return new Color(r1,g1,b1);
	}
	
	
	private int buildInt(Object[] oo) throws Exception
	{
		int sum = 0;
		for(int i=1;i<oo.length;i++) sum += toInt(oo[i]);
		return sum;
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Number) return ((Number) obj).intValue();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
