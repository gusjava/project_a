package a.entity.gus06.array.d2.objectarray.findall.min;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] input = (Object[][]) o[0];
		T t = (T) o[1];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		List output = new ArrayList();
		if(nb1==0 || nb2==0) return output;
		
		Object element0 = input[0];
		output.add(element0);
		Comparable minValue = (Comparable) t.t(element0);
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++) if(i>0 || j>0)
		{
			Object element = input[i][j];
			Comparable value = (Comparable) t.t(element);
			int r = value.compareTo(minValue);
			
			if(r<0)
			{
				minValue = value;
				output.clear();
				output.add(element);
			}
			else if(r==0)
			{
				output.add(element);
			}
		}
		return output;
	}
}
