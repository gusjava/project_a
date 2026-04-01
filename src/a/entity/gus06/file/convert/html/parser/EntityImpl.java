package a.entity.gus06.file.convert.html.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import a.framework.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150609";}



	public Object t(Object obj) throws Exception
	{return formatElement(buildDoc(obj));}
	
	
	
	
	private Document buildDoc(Object obj) throws Exception
	{
		if(obj instanceof String) return new Document((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Object formatElement(Element element)
	{
		Map map = new HashMap();
		map.put("name",element.nodeName());
		
		String data = element.ownText();
		if(data!=null) map.put("data",data);
		
		Map attributes = attributes(element);
		if(attributes!=null) map.put("attributes",attributes);
		
		List children = children(element);
		if(children!=null) map.put("children",children);
		
		return map;
	}
	
	
	
	
	private Map attributes(Element element)
	{
		List attributes = element.attributes().asList();
		int attributesNumber = attributes.size();
		if(attributesNumber==0) return null;
		
		Map map = new HashMap();
		for(int i=0;i<attributesNumber;i++)	
		{
			Attribute attr = (Attribute) attributes.get(i);
			String key = attr.getKey();
			String value = attr.getValue();
			map.put(key,value);
		}
		return map;
	}
	
	
	
	
	private List children(Element element)
	{
		Elements children = element.children();
		int childrenNumber = children.size();
		if(childrenNumber==0) return null;
		
		List list = new ArrayList();
		for(int i=0;i<childrenNumber;i++)	
		{
			Element child = children.get(i);
			list.add(formatElement(child));
		}
		return list;
	}
}