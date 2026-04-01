package a.entity.gus06.file.buildfilter.md5;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220208";}


	private Service buildMd5;
	
	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		return new Filter((String) obj);
	}
	
	private class Filter implements F
	{
		private String md5;
		public Filter(String md5) {this.md5 = md5.toUpperCase();}
		
		public boolean f(Object obj) throws Exception
		{
			File file = (File) obj;
			if(!file.isFile()) return false;
			
			String md5_ = (String) buildMd5.t(file);
			return md5.equals(md5_);
		}
	}
}