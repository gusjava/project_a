package a.entity.gus06.file.epub.build.coverfinder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}

	
	
	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		return new Finder(data);
	}
	
	
	private class Finder implements G
	{
		private Map data;
		public 	Finder(Map data) {this.data = data;}
		
		public Object g() throws Exception
		{return data.get("cover");}
	}
}
