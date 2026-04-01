package a.entity.gus06.dir.listing0.files.byext;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String ext = ((String) o[1]).toLowerCase();
		
		if(dir==null || !dir.isDirectory()) return null;
		return dir.listFiles(new Filter(ext));
	}
	
	private class Filter implements FileFilter
	{
		private String ext;
		public Filter(String ext)
		{this.ext = ext;}
		
		public boolean accept(File f)
		{
			if(!f.isFile()) return false;
			String name = f.getName().toLowerCase();
			return name.endsWith("."+ext);
		}
	}
}