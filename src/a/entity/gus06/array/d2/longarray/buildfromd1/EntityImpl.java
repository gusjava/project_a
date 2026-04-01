package a.entity.gus06.array.d2.longarray.buildfromd1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180108";}


	private Service findNb2;
	
	public EntityImpl() throws Exception
	{
		findNb2 = Outside.service(this,"gus06.array.d2.objectarray.buildfromd1.findn2");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] array = (Object[]) obj;
		
		int nb1 = array.length;
		if(nb1==0) return new int[0][0];
		
		int nb2 = ((Integer) findNb2.t(array)).intValue();
		long[][] table = new long[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		table[i][j] = findAt(array[i],j);
		
		return table;
	}
	
	
	private long findAt(Object row, int pos) throws Exception
	{
		if(row instanceof List)
		{
			List l = (List) row;
			return pos<l.size() ? toLong(l.get(pos)) : 0;
		}
		if(row instanceof long[])
		{
			long[] l = (long[]) row;
			return pos<l.length ? l[pos] : 0;
		}
		throw new Exception("Invalid row data type: "+row.getClass().getName());
	}
	
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer) obj).longValue();
		if(obj instanceof Long) return ((Long) obj).longValue();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
