package a.entity.gus06.string.transform.xhtml.indent;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170218";}

	public static final char INDENT = '\t';

	private Service evaluate;
	private Service display;


	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.xhtmlparser1.engine");
		display = Outside.service(this,"gus06.sys.xhtmlparser1.indentation");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return indent((String) obj);
	}
	
	
	public String indent(String s) throws Exception
	{
		String offset = getOffset(s);
		Object data = evaluate.t(s);
		String r = (String) display.t(data);
		return addOffset(r,offset);
	}
	
	
	
	private String getOffset(String s)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c==INDENT) b.append(c);
			else break;
		}
		return b.toString();
	}
	
	
	private String addOffset(String s, String offset)
	{
		StringBuffer b = new StringBuffer();
		String[] nn = s.split("\n");
		for(String n:nn) b.append(offset+n+"\n");
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}