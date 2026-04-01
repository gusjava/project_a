package a.entity.gus06.array.d2.objectarray.coord.xytolen;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] data = (Object[][]) o[0];
		int[] xy = (int[]) o[1];
		
		if(xy.length!=2) throw new Exception("Wrong xy number: "+xy.length);
		int x = xy[0];
		int y = xy[1];
		
		int nb1 = data.length;
		int nb2 = nb1>0 ? data[0].length : 0;
		
		return Integer.valueOf(x*nb2+y);
	}
}