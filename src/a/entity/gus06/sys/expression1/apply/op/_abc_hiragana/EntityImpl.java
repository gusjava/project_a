package a.entity.gus06.sys.expression1.apply.op._abc_hiragana;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180202";}

	public static final String T = "constant";


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.simple.japanese.hiragana");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return perform.g();
	}
}
