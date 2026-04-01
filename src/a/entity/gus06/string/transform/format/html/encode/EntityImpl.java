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
		if(c == '�') return "&aacute;";
		if(c == '�') return "&acirc;";
		if(c == '�') return "&aelig;";
		if(c == '�') return "&agrave;";
		if(c == '�') return "&aring;";       
		if(c == '�') return "&atilde;";
		if(c == '�') return "&auml;";
		if(c == '�') return "&ccedil;";
		if(c == '�') return "&eacute;";
		if(c == '�') return "&ecirc;";
		if(c == '�') return "&egrave;";
		if(c == '�') return "&euml;";
		if(c == '�') return "&iacute;";
		if(c == '�') return "&icirc;";
		if(c == '�') return "&igrave;";
		if(c == '�') return "&iuml;";
		if(c == '�') return "&ntilde;";
		if(c == '�') return "&oacute;";
		if(c == '�') return "&ocirc;";
		if(c == '�') return "&ograve;";
		if(c == '�') return "&oslash;"; 
		if(c == '�') return "&otilde;";
		if(c == '�') return "&ouml;";
		if(c == '�') return "&szlig;";
		if(c == '�') return "&thorn;"; 
		if(c == '�') return "&uacute;";
		if(c == '�') return "&ucirc;";
		if(c == '�') return "&ugrave;";
		if(c == '�') return "&uuml;";
		if(c == '�') return "&yacute;";
		if(c == '�') return "&yuml;";
		
		if(c == '\n') return "<br/>";
		if(c == '\r') return "";
		
		return "" + c;
	}
	
}
