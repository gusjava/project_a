package a.entity.gus06.app.jarfile.class1.listing;

import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180130";}

	private Service classpathListing;
	private List list;

	public EntityImpl() throws Exception
	{
		classpathListing = Outside.service(this,"gus06.app.jarfile.classpath.listing");
	}
	
	
	public Object g() throws Exception
	{
		if(list==null) init();
		return list;
	}
	
	
	private void init() throws Exception
	{
		list = new ArrayList();
		
		List list1 = (List) classpathListing.g();
		for(int i=0;i<list1.size();i++)
		{
			String classpath = (String) list1.get(i);
			Class c = Class.forName(classpath);
			list.add(c);
		}
	}
}
