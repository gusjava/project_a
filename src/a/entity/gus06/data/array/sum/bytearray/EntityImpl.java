package a.entity.gus06.data.array.sum.bytearray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160308";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		int length = 0;
		for(Object o:oo) length += ((byte[]) o).length;
		byte[] sum = new byte[length];
		
		int pos = 0;
		for(Object o:oo)
		{
			byte[] b = (byte[]) o;
			System.arraycopy(b, 0, sum, pos, b.length);
			pos += b.length;
		}
		return sum;
	}
}