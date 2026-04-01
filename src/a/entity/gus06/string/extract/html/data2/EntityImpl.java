package a.entity.gus06.string.extract.html.data2;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170328";}

	private Service extract;


	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.string.extract.html.data1");
	}


	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List list = (List) extract.t(s);
		
		List output = new ArrayList();
		
		for(int i=0;i<list.size();i++) if(i%2==0)
		{
			String element = (String) list.get(i);
			if(element!=null && !element.trim().equals(""))
			{
				String type = findType(list,i);
				output.add(new String[]{element,type});
			}
		}
		return output;
	}
	
	
	private String findType(List list, int index)
	{
		if(index==0 || index==list.size()-1) return null;
		
		String a1 = (String) list.get(index-1);
		String a2 = (String) list.get(index+1);
		
		if(!a2.startsWith("</")) return null;
		if(!a2.endsWith(">")) return null;
		
		String name = a2.substring(2,a2.length()-1).trim();
		
		if(!a1.startsWith("<"+name)) return null;
		return name;
	}
}
