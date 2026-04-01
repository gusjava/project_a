package a.entity.gus06.array.d2.booleanarray.buildfromd1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180108";}


	private Service findNb2;
	private Service findBoolean;
	
	public EntityImpl() throws Exception
	{
		findNb2 = Outside.service(this,"gus06.array.d2.objectarray.buildfromd1.findn2");
		findBoolean = Outside.service(this,"gus06.find.boolean1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] array = (Object[]) obj;
		
		int nb1 = array.length;
		if(nb1==0) return new int[0][0];
		
		int nb2 = ((Integer) findNb2.t(array)).intValue();
		boolean[][] table = new boolean[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		table[i][j] = findAt(array[i],j);
		
		return table;
	}
	
	
	private boolean findAt(Object row, int pos) throws Exception
	{
		if(row instanceof List)
		{
			List l = (List) row;
			return pos<l.size() ? toBoolean(l.get(pos)) : false;
		}
		if(row instanceof boolean[])
		{
			boolean[] l = (boolean[]) row;
			return pos<l.length ? l[pos] : false;
		}
		throw new Exception("Invalid row data type: "+row.getClass().getName());
	}
	
	
	private boolean toBoolean(Object obj) throws Exception
	{return findBoolean.f(obj);}
}
