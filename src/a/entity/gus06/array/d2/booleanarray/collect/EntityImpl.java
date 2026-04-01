package a.entity.gus06.array.d2.booleanarray.collect;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180107";}


	private Service findBoolean;
	
	public EntityImpl() throws Exception
	{
		findBoolean = Outside.service(this,"gus06.find.boolean1");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		boolean[][] input = (boolean[][]) o[0];
		Object[][] output = (Object[][]) t(obj);
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			input[i][j] = findBoolean.f(output[i][j]);
		}
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		boolean[][] input = (boolean[][]) o[0];
		T t = (T) o[1];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		Object[][] output = new Object[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			Boolean element = Boolean.valueOf(input[i][j]);
			output[i][j] = t.t(element);
		}
		return output;
	}
}