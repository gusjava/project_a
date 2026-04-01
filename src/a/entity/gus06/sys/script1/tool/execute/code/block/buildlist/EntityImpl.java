package a.entity.gus06.sys.script1.tool.execute.code.block.buildlist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170216";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[] lines = (String[]) o[0];
		String indent = (String) o[1];
		
		return indent!=null ? linesToList(lines,indent) : linesToList(lines);
	}
	
	
	private List linesToList(String[] lines)
	{
		List list = new ArrayList();
		for(String line:lines)
		{
			String s = line.trim();
			if(!s.equals("")) list.add(s);
		}
		return list;
	}
	
	
	private List linesToList(String[] lines, String indent) throws Exception
	{
		List list = new ArrayList();
		int level = 0;
		
		for(String line:lines)
		{
			String line_ = line.trim();
			if(!line_.equals(""))
			{
				int l = countOffset(line,indent);
				if(l>level+1) throw new Exception("Invalid indent at line: "+line);
				
				if(l==level+1) level++;
				else if(l<level) 
				{
					for(int i=0;i<level-l;i++) list.add("end");
					level=l;
				}
				list.add(line_);
			}
		}
		return list;
	}
	
	private int countOffset(String line, String indent)
	{
		int n = 0;
		int len = indent.length();
		while(line.startsWith(indent))
		{
			line = line.substring(len);
			n++;
		}
		return n;
	}
}
