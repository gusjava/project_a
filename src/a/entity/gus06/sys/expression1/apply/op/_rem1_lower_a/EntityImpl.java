package a.entity.gus06.sys.expression1.apply.op._rem1_lower_a;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160822";}
	
	public static final String KEY = "lower";


	private Service regexFromRule;
	private String regex;
	
	public EntityImpl() throws Exception
	{
		regexFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		regex = (String) regexFromRule.r(KEY);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return rem((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String rem(String s) throws Exception
	{
		return s.replaceAll(regex,"");
	}
	
}
