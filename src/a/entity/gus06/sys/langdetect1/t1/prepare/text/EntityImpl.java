package a.entity.gus06.sys.langdetect1.t1.prepare.text;

import a.framework.*;
import java.lang.Character.UnicodeBlock;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}
	
	public static final int MAX_TEXT_LENGTH = 10000;

	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		StringBuffer buff = new StringBuffer();
	
		// NORMALIZING ...
		
		text = NORMALIZE_VI.normalize_vi(text);
		char pre = 0;
		int len = Math.min(text.length(), MAX_TEXT_LENGTH);
		
		for(int i = 0; i < len; ++i)
		{
			char c = text.charAt(i);
			if (c != ' ' || pre != ' ') buff.append(c);
			pre = c;
		}
		
		// CLEANING ... 
		
		int latinCount = 0;
		int nonLatinCount = 0;
		
		for(int i = 0; i < buff.length(); ++i)
		{
			char c = buff.charAt(i);
			if (c <= 'z' && c >= 'A')
				++latinCount;
			else if (c >= '\u0300' && UnicodeBlock.of(c) != UnicodeBlock.LATIN_EXTENDED_ADDITIONAL)
				++nonLatinCount;
		}
		
		if(latinCount * 2 < nonLatinCount)
		{
			StringBuffer textWithoutLatin = new StringBuffer();
			for(int i = 0; i < buff.length(); ++i)
			{
				char c = buff.charAt(i);
				if (c > 'z' || c < 'A') textWithoutLatin.append(c);
			}
			buff = textWithoutLatin;
		}
		return buff.toString();
	}
}