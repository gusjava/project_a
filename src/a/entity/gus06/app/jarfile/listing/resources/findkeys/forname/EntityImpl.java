package a.entity.gus06.app.jarfile.listing.resources.findkeys.forname;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}


	private Service ressourcesList;


	public EntityImpl() throws Exception
	{
		ressourcesList = Outside.service(this,"gus06.app.jarfile.listing.resources");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		List list = (List) ressourcesList.g();
		
		List output = new ArrayList();
		
		for(Object o:list)
		{
			String path = (String) o;
			String[] n = path.split("/");
			if(n.length==6 && n[4].equals(name))
				output.add(n[5]);
		}
		return output;
	}
}
