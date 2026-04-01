package a.entity.gus06.string.transform.generate.range.character;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160512";}


	private Service alphabetLower;
	private Service alphabetUpper;
	private Service alphabetDigit;
	private Service japaneseHiragana;
	private Service japaneseKatakana;

	public EntityImpl() throws Exception
	{
		alphabetLower = Outside.service(this,"gus06.string.transform.simple.alphabet.lower");
		alphabetUpper = Outside.service(this,"gus06.string.transform.simple.alphabet.upper");
		alphabetDigit = Outside.service(this,"gus06.string.transform.simple.digits");
		japaneseHiragana = Outside.service(this,"gus06.string.transform.simple.japanese.hiragana");
		japaneseKatakana = Outside.service(this,"gus06.string.transform.simple.japanese.katakana");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(":",2);
		
		String type = n[0].trim();
		String delim = n.length==2?n[1]:"";
		
		boolean inv = false;
		if(type.endsWith(" inv"))
		{
			inv = true;
			type = type.substring(0,type.length()-3).trim();
		}
		
		if(type.equals("lower")) return buildRange((String) alphabetLower.g(),delim,inv);
		if(type.equals("upper")) return buildRange((String) alphabetUpper.g(),delim,inv);
		if(type.equals("digit")) return buildRange((String) alphabetDigit.g(),delim,inv);
		if(type.equals("hiragana")) return buildRange((String) japaneseHiragana.g(),delim,inv);
		if(type.equals("katakana")) return buildRange((String) japaneseKatakana.g(),delim,inv);
		
		String[] k = type.split(" ");
		int start = int_(k[0]);
		int end = int_(k[1]);
		
		return buildRange(start,end,delim,inv);
	}
	
	
	private int int_(String s)
	{return Integer.parseInt(s);}
	
	
	
	
	private String buildRange(String all, String delim, boolean inv)
	{
		if(delim.equals("") && !inv) return all;
		
		int len = all.length();
		StringBuffer b = new StringBuffer();
		
		if(inv)
		for(int i=len-1;i>=0;i--)
		{
			b.append(all.charAt(i));
			if(i>0) b.append(delim);
		}
		else
		for(int i=0;i<len;i++)
		{
			b.append(all.charAt(i));
			if(i<len-1) b.append(delim);
		}
		
		return b.toString();
	}
	
	
	
	private String buildRange(int start0, int end0, String delim, boolean inv)
	{
		int start = inv ? end0 : start0;
		int end = inv ? start0 : end0;
		
		StringBuffer b = new StringBuffer();
		if(start<=end)
		{
			for(int i=start;i<=end;i++)
			{
				b.append(Character.toChars(i));
				if(i<end) b.append(delim);
			}
		}
		else
		{
			for(int i=start;i>=end;i--)
			{
				b.append(Character.toChars(i));
				if(i>end) b.append(delim);
			}
		}
		return b.toString();
	}
}
