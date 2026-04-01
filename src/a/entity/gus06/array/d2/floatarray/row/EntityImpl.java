package a.entity.gus06.array.d2.floatarray.row;

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
		
		float[][] input = (float[][]) o[0];
		Object rule = o[1];
		
		int nb1 = input.length;
		Integer x = (Integer) ruleToIndex.t(new Object[]{nb1,rule});
		return input[x.intValue()];
	}
}
