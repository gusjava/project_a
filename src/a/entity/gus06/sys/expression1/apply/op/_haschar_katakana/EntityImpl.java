package a.entity.gus06.sys.expression1.apply.op._haschar_katakana;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190316";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.filter.string.haschar.japanese.katakana");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof String)) return Boolean.FALSE;
		return Boolean.valueOf(perform.f(obj));
	}
}
