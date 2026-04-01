package a.entity.gus06.sys.treeparser1.engine;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161016";}
	
	public static final String NAME = "name";
	public static final String INDEX = "index";
	public static final String CHILDREN = "children";
	public static final String DATA = "data";


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		String[] nn = s.split("\n");
		List input = new ArrayList(Arrays.asList(nn));
		int total = input.size();
		
		List children = new ArrayList();
		StringBuffer b = new StringBuffer();
		
		handle(input,total,children,b,0);
		
		Map map = new HashMap();
		map.put(NAME,"ROOT");
		map.put(CHILDREN,children);
		map.put(DATA,b.toString());
		
		return map;
	}
	
	
	
	private void handle(List input, int total, List children, StringBuffer b, int level) throws Exception
	{
		while(!input.isEmpty())
		{
			String line = (String) input.get(0);
			int index = total-input.size();
			int level1 = findLevel(line);
			
			if(level1==0)
			{
				input.remove(0);
				b.append(line+"\n");
			}
			else if(level1<=level)
			{
				return;
			}
			else if(level1==level+1)
			{
				input.remove(0);
				
				String name = line.substring(level1);
				StringBuffer b1 = new StringBuffer();
				List children1 = new ArrayList();
				
				handle(input,total,children1,b1,level+1);
				
				Map map = new HashMap();
				map.put(NAME,name);
				map.put(INDEX,Integer.valueOf(index));
				map.put(CHILDREN,children1);
				map.put(DATA,b1.toString());
				
				children.add(map);
			}
			else throw new Exception("Invalid syntax at index: "+index);
		}
		
	}
	
	
	private int findLevel(String line)
	{
		for(int i=0;i<line.length();i++)
		if(line.charAt(i)!='@') return i;
		return line.length();
	}
}
