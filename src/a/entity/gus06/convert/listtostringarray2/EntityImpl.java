package a.entity.gus06.convert.listtostringarray2;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190713";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int nb1 = l.size();
		int nb2 = findNb2(l);
		
		String[][] yy = new String[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		yy[i][j] = findAt(l.get(i),j);
		
		return yy;
	}
	
	
	
	private int findNb2(List l) throws Exception
	{
		int nb = 0;
		for(Object element : l)
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
		if(element instanceof int[]) return ((int[]) element).length;
		if(element instanceof double[]) return ((double[]) element).length;
		if(element instanceof float[]) return ((float[]) element).length;
		if(element instanceof long[]) return ((long[]) element).length;
		if(element instanceof boolean[]) return ((boolean[]) element).length;
		
		throw new Exception("Invalid row data type: "+element.getClass().getName());
	}
	
	
	private String findAt(Object row, int pos) throws Exception
	{
		if(row instanceof List)
		{
			List l = (List) row;
			return pos<l.size() ? ""+l.get(pos) : null;
		}
		if(row instanceof Object[])
		{
			Object[] l = (Object[]) row;
			return pos<l.length ? ""+l[pos] : null;
		}
		throw new Exception("Invalid row data type: "+row.getClass().getName());
	}
}
