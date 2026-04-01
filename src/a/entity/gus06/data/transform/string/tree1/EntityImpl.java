package a.entity.gus06.data.transform.string.tree1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}


	private Service stringToList;


	public EntityImpl() throws Exception
	{
		stringToList = Outside.service(this,"gus06.convert.stringtolist");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List input = (List) stringToList.t(s);
		
		List output = new ArrayList();
		while(!input.isEmpty()) handle(output,input,0);
		
		return output;
	}
	
	
	private void handle(List output, List input, int level) throws Exception
	{
		while(!input.isEmpty())
		{
			String line = (String) input.remove(0);
			
			if(line.trim().equals("")) continue;
			if(line.startsWith("#")) continue;
			
			int deep = findDeep(line);
			
			if(deep==level)
			{
				Map m = new HashMap();
				m.put("children",new ArrayList());
				m.put("content",line.substring(deep));
				
				output.add(m);
			}
			else if(deep==level+1)
			{
				if(output.isEmpty()) throw new Exception("Invalid syntax for tree file at line: "+line+" level="+level+" deep="+deep);
				Map m = (Map) output.get(output.size()-1);
				
				List l = (List) m.get("children");
				input.add(0,line);
				handle(l,input,deep);
			}
			else if(deep<level)
			{
				input.add(0,line);
				break;
			}
			else throw new Exception("Invalid syntax for tree file at line: "+line+" level="+level+" deep="+deep);
		}
	}
	
	
	
	private int findDeep(String line)
	{
		for(int i=0;i<line.length();i++)
		if(line.charAt(i)!='\t') return i;
		return line.length();
	}
}
