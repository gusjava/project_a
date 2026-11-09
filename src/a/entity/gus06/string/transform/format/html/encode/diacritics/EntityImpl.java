package a.entity.gus06.string.transform.format.html.encode.diacritics;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141105";}

	
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		StringBuffer b = new StringBuffer();
		for(int i=0;i<input.length();i++)
		b.append(encode(input.charAt(i)));
		return b.toString();
	}
	
	
	private String encode(char c)
	{
		if(c == 'á') return "&aacute;";
		if(c == 'â') return "&acirc;";
		if(c == 'æ') return "&aelig;";
		if(c == 'à') return "&agrave;";
		if(c == 'å') return "&aring;";       
		if(c == 'ã') return "&atilde;";
		if(c == 'ä') return "&auml;";
		if(c == 'ç') return "&ccedil;";
		if(c == 'é') return "&eacute;";
		if(c == 'ê') return "&ecirc;";
		if(c == 'è') return "&egrave;";
		if(c == 'ë') return "&euml;";
		if(c == 'í') return "&iacute;";
		if(c == 'î') return "&icirc;";
		if(c == 'ì') return "&igrave;";
		if(c == 'ï') return "&iuml;";
		if(c == 'ñ') return "&ntilde;";
		if(c == 'ó') return "&oacute;";
		if(c == 'ô') return "&ocirc;";
		if(c == 'ò') return "&ograve;";
		if(c == 'ø') return "&oslash;"; 
		if(c == 'õ') return "&otilde;";
		if(c == 'ö') return "&ouml;";
		if(c == 'ß') return "&szlig;";
		if(c == 'þ') return "&thorn;"; 
		if(c == 'ú') return "&uacute;";
		if(c == 'û') return "&ucirc;";
		if(c == 'ù') return "&ugrave;";
		if(c == 'ü') return "&uuml;";
		if(c == 'ý') return "&yacute;";
		if(c == 'ÿ') return "&yuml;";
		
		return "" + c;
	}
	
}
