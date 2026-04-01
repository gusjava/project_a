package a.entity.gus06.data.perform.cell.d8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}


	private Service performArray2;
	private Service findArray2;
	
	public EntityImpl() throws Exception
	{
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.cell.d8");
		findArray2 = Outside.service(this,"gus06.find.objectarray2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Object xRule = o[1];
		Object yRule = o[2];
		
		data = findArray2.t(data);
		
		if(data instanceof Object[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof double[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof int[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof long[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof float[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof boolean[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof char[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof byte[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		if(data instanceof short[][])		return performArray2.t(new Object[]{data,xRule,yRule});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
