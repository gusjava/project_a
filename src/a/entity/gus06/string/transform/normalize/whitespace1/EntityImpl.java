package a.entity.gus06.string.transform.normalize.whitespace1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160729";}
	
	public static final int WHITESPACE = 0;
	public static final int LETTER_DIGIT = 1;
	public static final int OTHER = 2;
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		
		StringBuffer b = new StringBuffer();
		int n = -1;
		
		int length = s.length();
		for(int i=0;i<length;i++)
		{
			char c = s.charAt(i);
			int k = valueFor(c);
			insertSpace(b,n,k);
			if(k!=WHITESPACE) b.append(c);
			n = k;
		}
		return b.toString();
	}
	
	
	
	private int valueFor(char c)
	{
		if(Character.isWhitespace(c)) return WHITESPACE;
		if(Character.isLetterOrDigit(c)) return LETTER_DIGIT;
		return OTHER;
	}
	
	private void insertSpace(StringBuffer b, int n, int k)
	{
		if(n==-1) return;
		if(k==WHITESPACE) return;
		if(k==LETTER_DIGIT && n==LETTER_DIGIT) return;
		
		b.append(" ");
	}
}
