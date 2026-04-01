package a.entity.gus06.array.d2.longarray.col;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180116";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		long[][] input = (long[][]) o[0];
		Object rule = o[1];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		Integer y = (Integer) ruleToIndex.t(new Object[]{nb2,rule});
		
		long[] col = new long[nb1];
		for(int i=0;i<nb1;i++)
		col[i] = input[i][y.intValue()];
		
		return col;
	}
}
