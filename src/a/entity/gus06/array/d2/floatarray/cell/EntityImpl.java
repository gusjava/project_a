package a.entity.gus06.array.d2.floatarray.cell;

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
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		float[][] input = (float[][]) o[0];
		Object xRule = o[1];
		Object yRule = o[2];
		
		int nb1 = input.length;
		int nb2 = nb1>0 ? input[0].length : 0;
		
		Integer x = (Integer) ruleToIndex.t(new Object[]{nb1,xRule});
		Integer y = (Integer) ruleToIndex.t(new Object[]{nb2,yRule});
		
		return input[x.intValue()][y.intValue()];
	}
}
