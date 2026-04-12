/*
 * Created on 5 mars 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package a.entity.gus06.string.transform2.replace.regexp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;


public class CompReplaceRegexTransform implements T {

	
	public final static String OCC_EXP = "$";
	public final static String INDEX_EXP = "<i>";
	
	
	private String regexp;
	private String replacement;
	

	public CompReplaceRegexTransform(String selected)
	{
		String[] n = selected.split("[\n\r]",-1);
		regexp = n[0];
		
		if(n.length>1)
			replacement = n[1];
		else replacement = "";
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		if(replacement.contains(OCC_EXP) || replacement.contains(INDEX_EXP))
			return replaceBackRef(obj.toString());
		return obj.toString().replaceAll(regexp,replacement);
	}
	
	
	
	/*
	 * J'ai d�couvert que la classe Match permet de faire cette op�ration...
	 * utiliser :
	 */
	private String replaceBackRef(String in)
	{
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(in);
		
		StringBuffer b = new StringBuffer();
		int end0 = 0;
		
		int index = 0;
		while(m.find())
		{
			index++;
			String occ = m.group();
			String rep = replacement.replace(OCC_EXP,occ).replace(INDEX_EXP,""+index);
			
			b.append(in.substring(end0,m.start()));
			b.append(rep);
			end0 = m.end();
		}
		b.append(in.substring(end0,in.length()));
		return b.toString();
	}

}
