package a.entity.gus06.data.perform.html.gen.ul;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190709";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return performList((List) obj);
		if(obj instanceof String[]) return performArray((String[]) obj);
		if(obj instanceof String) return performString((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String performList(List list)
	{
		StringBuffer b = new StringBuffer();
		b.append("<ul>\n");
		
		for(int i=0;i<list.size();i++)
		{
			String element = format((String) list.get(i));
			if(!element.equals(""))
			{
				b.append("\t<li>");
				b.append(element);
				b.append("</li>\n");
			}
		}
		b.append("</ul>\n");
		return b.toString();
	}
	
	
	private String performArray(String[] array)
	{
		StringBuffer b = new StringBuffer();
		b.append("<ul>\n");
		
		for(int i=0;i<array.length;i++)
		{
			String element = format(array[i]);
			if(!element.equals(""))
			{
				b.append("\t<li>");
				b.append(element);
				b.append("</li>\n");
			}
		}
		b.append("</ul>\n");
		return b.toString();
	}
	
	
	private String performString(String s)
	{
		String[] nn = s.split("\n");
		return performArray(nn);
	}
	
	
	private String format(String element)
	{
		if(element==null) return "";
		element = element.trim();
		if(element.startsWith("-")) element = element.substring(1).trim();
		if(element.startsWith("\u2022")) element = element.substring(1).trim();
		if(element.startsWith("\u006f")) element = element.substring(1).trim();
		return element;
	}

}
