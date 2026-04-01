package a.entity.gus06.data.string.freqmap.counttexts_n;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210716";}


	private Service regexQuote;
	private Service normalize;
	
	public EntityImpl() throws Exception
	{
		regexQuote = Outside.service(this,"gus06.string.regex.quote");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) normalize.t(o[0]);
		String[] words = (String[]) o[1];
		
		Map output = new HashMap();
		for(int i=0;i<words.length;i++)
		{
			int occ = findOcc(input,words[i]);
			output.put(words[i], Integer.valueOf(occ));
		}
		return output;
	}
	
	
	private int findOcc(String input, String word) throws Exception
	{
		Pattern p = (Pattern) regexQuote.t(word);
		Matcher m = p.matcher(input);
		int occ = 0;
		while(m.find()) occ++;
		return occ;
	}
}