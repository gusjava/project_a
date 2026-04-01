package a.entity.gus06.array.d2.objectarray.coord.lentoxy;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] data = (Object[][]) o[0];
		int len = toInt(o[1]);
		
		int nb1 = data.length;
		int nb2 = nb1>0 ? data[0].length : 0;
		
		if(len>nb1*nb2) return null;
		
		int x = len/nb2;
		int y = len%nb2;
		
		return new int[]{x,y};
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}