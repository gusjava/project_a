package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.font;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190509";}




	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Font font = (Font) oo[0];
		
		int nb = buildInt(oo);
		int newSize = font.getSize()+nb;
		
		return font.deriveFont((float) newSize);
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
