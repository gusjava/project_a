package a.entity.gus06.sys.expression1.apply.op._is_string_vowel;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201229";}
	
	public static final String VOWELS = "aeiouy\u00e2\u00ea\u00ee\u00f4\u00fb\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00e9\u00e8\u00e0\u00f9";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof String)) return Boolean.FALSE;
		
		String s = (String) obj;
		return Boolean.valueOf(s.matches("["+VOWELS+"]+"));
	}
}