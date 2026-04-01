package a.entity.gus06.string.transform.generate.java.setter;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160823";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.java.srccode.generate.setter");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] lines = s.split("\n");
		if(lines.length==0) return "";
		
		StringBuffer b = new StringBuffer();
		
		for(String line:lines)
		if(!line.trim().equals(""))
		{
			b.append((String) perform.t(line));
			b.append("\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
