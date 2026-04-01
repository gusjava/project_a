package a.entity.gus06.sys.expression1.apply.op._is_char_kanji;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160817";}
	
	public static final String BLOCKS = "CJK_UNIFIED_IDEOGRAPHS";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof String)) return Boolean.FALSE;
		
		String s = (String) obj;
		if(s.length()!=1) return Boolean.FALSE;
		
		return Boolean.valueOf(isValid(s.charAt(0)));
	}
	
	
	private boolean isValid(char c)
	{return BLOCKS.contains(Character.UnicodeBlock.of(c).toString());}
}