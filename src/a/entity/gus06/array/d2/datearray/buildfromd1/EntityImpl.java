package a.entity.gus06.array.d2.datearray.buildfromd1;

import a.framework.*;
import java.util.List;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180331";}


	private Service findNb2;
	
	public EntityImpl() throws Exception
	{
		findNb2 = Outside.service(this,"gus06.array.d2.objectarray.buildfromd1.findn2");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] array = (Object[]) obj;
		
		int nb1 = array.length;
		if(nb1==0) return new Object[0][0];
		
		int nb2 = ((Integer) findNb2.t(array)).intValue();
		Date[][] table = new Date[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		table[i][j] = findAt(array[i],j);
		
		return table;
	}
	
	
	private int findNb2(Object[] array) throws Exception
	{
		int nb = 0;
		for(Object element : array)
		{
			int n = findNb(element);
			if(n>nb) nb = n;
		}
		return nb;
	}
	
	
	private int findNb(Object element) throws Exception
	{
		if(element instanceof List) return ((List) element).size();
		if(element instanceof Object[]) return ((Object[]) element).length;
		
		throw new Exception("Invalid row data type: "+element.getClass().getName());
	}
	
	
	private Date findAt(Object row, int pos) throws Exception
	{
		if(row instanceof List)
		{
			List l = (List) row;
			return pos<l.size() ? (Date) l.get(pos) : null;
		}
		if(row instanceof Date[])
		{
			Date[] l = (Date[]) row;
			return pos<l.length ? l[pos] : null;
		}
		throw new Exception("Invalid row data type: "+row.getClass().getName());
	}
}
