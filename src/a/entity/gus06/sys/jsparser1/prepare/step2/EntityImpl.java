package a.entity.gus06.sys.jsparser1.prepare.step2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221012";}
	
	public static final String SLASH = "/";
	public static final String QUOTE = "'";
	public static final String DQUOTE = "\"";
	public static final String ESCAPE = "\\";
	public static final String LF = "\\n";


	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_COMMENT = "comment";
	public static final String TYPE_OTHER = "other";
	
	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		List output = new ArrayList();
		
		while(!input.isEmpty())
		{
			Map m = buildNext(input);
			if(m!=null) output.add(m);
		}
		return output;
	}
	
	
	
	private Map buildNext(List input) throws Exception
	{
		Map m = removeNext(input);
		
		if(isQuote(m)) return buildString(input,QUOTE);
		if(isDQuote(m)) return buildString(input,DQUOTE);
		
		if(isSlash(m) && isSlash(getNext(input)))
			return buildCommentLine(input);
			
		if(isOther(m)) return null;
		return m;
	}
	
	
	
	private Map buildString(List input, String meta) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		boolean escapeFound = false;
		boolean endFound = false;
		
		while(!input.isEmpty() && !endFound)
		{
			Map m = removeNext(input);
			if(escapeFound)
			{
				if(isEscape(m)) b.append(ESCAPE);
				else if(hasValue(m,"/")) b.append("/"); //certains json en ont besoin ...
				else if(hasValue(m,meta)) b.append(meta);
				
				else if(startsWith(m,"b")) b.append("\b"+value(m).substring(1));
				else if(startsWith(m,"f")) b.append("\f"+value(m).substring(1));
				else if(startsWith(m,"n")) b.append("\n"+value(m).substring(1));
				else if(startsWith(m,"r")) b.append("\r"+value(m).substring(1));
				else if(startsWith(m,"t")) b.append("\t"+value(m).substring(1));
				else if(startsWith(m,"u")) handleHexa(b,input,m);
				
				else throw new Exception("Invalid string escape character at: "+b);
				
				escapeFound = false;
			}
			else
			{
				if(isEscape(m)) escapeFound = true;
				else if(hasValue(m,meta)) endFound = true;
				else b.append(value(m));
			}
		}
		
		Map m = new HashMap();
		m.put(TYPE,TYPE_STRING);
		m.put(VALUE,b.toString());
		
		return m;
	}
	
	
	
	private Map buildCommentLine(List input)
	{
		removeNext(input);
		StringBuffer b = new StringBuffer();
		
		while(!input.isEmpty() && !isLF(getNext(input)))
		{
			Map m = removeNext(input);
			b.append(value(m));
		}
		
		Map m = new HashMap();
		m.put(TYPE, TYPE_COMMENT);
		m.put(VALUE,b.toString());
		
		return m;
	}
	
	
	
	private Map removeNext(List input)
	{return (Map) input.remove(0);}
	
	private Map getNext(List input)
	{return (Map) input.get(0);}
	
	
	
	private String value(Map m)
	{return (String) m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
	
	private boolean hasValue(Map m, String value)
	{return value(m).equals(value);}
	
	private boolean startsWith(Map m, String value)
	{return value(m).startsWith(value);}
	
	private boolean hasType(Map m, String type)
	{return type(m).equals(type);}
	
	
	
	
	private boolean isSlash(Map m)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,SLASH);}
	
	private boolean isLF(Map m)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,LF);}
	
	private boolean isQuote(Map m)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,QUOTE);}
	
	private boolean isDQuote(Map m)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,DQUOTE);}
	
	private boolean isEscape(Map m)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,ESCAPE);}
	
	private boolean isOther(Map m)
	{return hasType(m,TYPE_OTHER);}
	






	private void handleHexa(StringBuffer b, List input, Map m) throws Exception
	{
		StringBuffer h = new StringBuffer();
		h.append(value(m).substring(1));
		
		while(h.length()<4 && !input.isEmpty())
		{
			m = (Map) input.remove(0);
			h.append(value(m));
		}
		if(h.length()<4) throw new Exception("Invalid \\u hexa value: "+h);
		
		int codePoint = Integer.parseInt(h.substring(0,4),16);
		b.append(Character.toChars(codePoint));
		b.append(h.substring(4));
	}
}