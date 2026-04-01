package a.entity.gus06.file.buildfilter.sizefilter;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220208";}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Filter((F) obj);
	}
	
	private class Filter implements F
	{
		private F filter;
		public Filter(F filter) {this.filter = filter;}
		
		public boolean f(Object obj) throws Exception
		{
			File file = (File) obj;
			if(!file.isFile()) return false;
			
			return filter.f(file.length());
		}
	}
}