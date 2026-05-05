package a.entity.gus06.appli.textcomparator.analyzer;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150305";}
	
	
	public static final String KEY_T1_LENGTH = "text1.length";
	public static final String KEY_T2_LENGTH = "text2.length";
	public static final String KEY_C_LENGTH = "common.length";
	public static final String KEY_T1_MD5 = "text1.md5";
	public static final String KEY_T2_MD5 = "text2.md5";
	public static final String KEY_EQUALS = "equals";
	public static final String KEY_DIFFCHARS = "diffchars";


	private Service md5;


	public EntityImpl() throws Exception
	{
		md5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String text1 = o[0];
		String text2 = o[1];
		
		int l1 = text1.length();
		int l2 = text2.length();
		
		String m1 = (String) md5.t(text1);
		String m2 = (String) md5.t(text2);
		
		boolean equals = text1.equals(text2);
		int lc = equals?l1:commonLength(text1,text2);
		
		Map map = new HashMap();
		
		map.put(KEY_T1_LENGTH,""+l1);
		map.put(KEY_T2_LENGTH,""+l2);
		map.put(KEY_C_LENGTH,""+lc);
		
		map.put(KEY_T1_MD5,""+m1);
		map.put(KEY_T2_MD5,""+m2);
		
		map.put(KEY_EQUALS,""+equals);
		
		if(lc<l1 && lc<l2)
		{
			char[] diff = new char[]{text1.charAt(lc),text2.charAt(lc)};
			map.put(KEY_DIFFCHARS,new String(diff));
		}
		return map;
	}
	
	
	
	private int commonLength(String s1, String s2)
	{
		int l = Math.min(s1.length(),s2.length());
		for(int i=0;i<l;i++)
		{
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if(c1!=c2) return i;
		}
		return l;
	}
}
