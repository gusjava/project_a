package a.entity.gus06.dir.perform.scanfiles.byext;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190308";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input =(File) o[0];
		String ext = ((String) o[1]).toLowerCase();
		
		return new Scanner(input,ext);
	}
	
	
	private class Scanner implements G
	{
		private String ext;
		private List found;
		private List dirPending;
		
		public Scanner(File root, String ext) throws Exception
		{
			if(root==null) throw new Exception("Invalid root: null");
			if(!root.isDirectory()) throw new Exception("Invalid root: "+root);
			
			this.ext = ext;
			
			found = new ArrayList();
			dirPending = new ArrayList();
			dirPending.add(root);
		}
		
		public Object g() throws Exception
		{
			while(found.isEmpty() && !dirPending.isEmpty()) scanNext();
			return found.isEmpty() ? null : found.remove(0);
		}
		
		private void scanNext()
		{
			File dir = (File) dirPending.remove(0);
			File[] ff = dir.listFiles();
			if(ff!=null) for(File f : ff)
			{
				if(f.isDirectory()) dirPending.add(f);
				else if(filter(f,ext)) found.add(f);
			}
		}
		
		private boolean filter(File file, String ext)
		{return file.getName().toLowerCase().endsWith("."+ext);}
	}
}
