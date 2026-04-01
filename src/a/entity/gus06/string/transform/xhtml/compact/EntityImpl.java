package a.entity.gus06.string.transform.xhtml.compact;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170301";}


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.normalize.whitespace");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String offset = getOffset(s);
		String r = (String) perform.t(s);
		
		return offset+r;
	}
	
	
	private String getOffset(String s)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c=='\t') b.append(c);
			else break;
		}
		return b.toString();
	}
}
