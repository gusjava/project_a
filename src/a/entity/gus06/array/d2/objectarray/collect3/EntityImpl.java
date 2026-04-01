package a.entity.gus06.array.d2.objectarray.collect3;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180115";}


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.array.d2.objectarray.findall3.buildmap");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] input = (Object[][]) o[0];
		Object[][] output = (Object[][]) t(obj);
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			input[i][j] = output[i][j];
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] input = (Object[][]) o[0];
		T t = (T) o[1];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		 
		Object[][] output = new Object[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			Integer index1 = Integer.valueOf(i);
			Integer index2 = Integer.valueOf(j);
			
			Map m = (Map) buildMap.t(new Object[]{input,index1,index2});
			output[i][j] = t.t(m);
		}
		return output;
	}
}
