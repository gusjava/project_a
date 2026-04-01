package a.entity.gus06.array.d2.objectarray.findall3;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.array.d2.objectarray.findall3.buildmap");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[][] input = (Object[][]) o[0];
		F filter = (F) o[1];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		List output = new ArrayList();
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			Integer index1 = Integer.valueOf(i);
			Integer index2 = Integer.valueOf(j);
			
			Map m = (Map) buildMap.t(new Object[]{input,index1,index2});
			if(filter.f(m)) output.add(input[i]);
		}
		return output;
	}
}
