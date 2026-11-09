package a.entity.gus06.string.transform.format.html.encode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}

	
	
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
		if(c == '<') return "&lt;";
		if(c == '>') return "&gt;";
		if(c == '#') return "&#35;";
		if(c == '$') return "&#36;";
		if(c == '%') return "&#37;";
		if(c == '&') return "&#38;";
		if(c == '/') return "&#47;";
		if(c == ':') return "&#58;";
		if(c == ';') return "&#59;";
		if(c == '?') return "&#63;";
		if(c == '{') return "&#123;";
		if(c == '|') return "&#124;";
		if(c == '}') return "&#125;";
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
		
		if(c == '\n') return "<br/>";
		if(c == '\r') return "";
		
		return "" + c;
	}
	
}
