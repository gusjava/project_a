package a.entity.gus06.sys.expression1.apply.op._regex_email;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160611";}
	
	public static final String KEY_EMAIL = "email";


	private Service regexFromRule;
	
	public EntityImpl() throws Exception
	{
		regexFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return regexFromRule.r(KEY_EMAIL);
	}
}
