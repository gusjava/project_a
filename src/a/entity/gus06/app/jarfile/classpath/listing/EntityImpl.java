package a.entity.gus06.app.jarfile.classpath.listing;

import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180130";}

	private Service entryListing;
	private List list;

	public EntityImpl() throws Exception
	{
		entryListing = Outside.service(this,"gus06.app.jarfile.listing.class1");
	}
	
	
	public Object g() throws Exception
	{
		if(list==null) init();
		return list;
	}
	
	
	private void init() throws Exception
	{
		list = new ArrayList();
		
		List list1 = (List) entryListing.g();
		for(int i=0;i<list1.size();i++)
		{
			String entryName = (String) list1.get(i);
			String classPath = entryNameToClassPath(entryName);
			
			if(classPath!=null) list.add(classPath);
		}
	}
	
	
	public static final String END = ".class";
	
	private String entryNameToClassPath(String entryName)
	{
		if(!entryName.endsWith(END)) return null;
		return entryName.substring(0,entryName.length()-END.length()).replace("/",".");
	}
}
