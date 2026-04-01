package a.entity.gus06.dir.listing0.mstars;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180320";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String m = (String) o[1];
		
		return dir.listFiles(new FileFilter1(m));
	}
	
	
	private class FileFilter1 implements FileFilter
	{
		private String m;
		private boolean start;
		private boolean end;
		private boolean all;
		
		public FileFilter1(String m)
		{
			all = m.equals("*");
			if(all) return;
			
			start = m.startsWith("*");
			end = m.endsWith("*");
			
			if(start && end) this.m = m.substring(1,m.length()-1);
			else if(start) this.m = m.substring(1);
			else if(end) this.m = m.substring(0,m.length()-1);
			else this.m = m;
		}
		
		public boolean accept(File f)
		{
			if(!f.exists()) return false;
			if(all) return true;
			
			String name = f.getName();
			
			if(start && end) return name.contains(m);
			if(start) return name.endsWith(m);
			if(end) return name.startsWith(m);
			return name.equals(m);
		}
	}
}
