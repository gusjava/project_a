package a.entity.gus06.dir.perform.scanfiles.byname0_n;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190316";}
	
	private Service getName0;
	private Service normalize;
	
	public EntityImpl() throws Exception
	{
		getName0 = Outside.service(this,"gus.x.file.getname0");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
	}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input =(File) o[0];
		String name = normalize((String) o[1]);
		
		return new Scanner(input,name);
	}
	
	
	private class Scanner implements G
	{
		private String name;
		private List found;
		private List dirPending;
		
		public Scanner(File root, String name) throws Exception
		{
			if(root==null) throw new Exception("Invalid root: null");
			if(!root.isDirectory()) throw new Exception("Invalid root: "+root);
			
			this.name = name;
			
			found = new ArrayList();
			dirPending = new ArrayList();
			dirPending.add(root);
		}
		
		public Object g() throws Exception
		{
			while(found.isEmpty() && !dirPending.isEmpty()) scanNext();
			return found.isEmpty() ? null : found.remove(0);
		}
		
		private void scanNext() throws Exception
		{
			File dir = (File) dirPending.remove(0);
			File[] ff = dir.listFiles();
			if(ff!=null) for(File f : ff)
			{
				if(f.isDirectory()) dirPending.add(f);
				else if(filter(f,name)) found.add(f);
			}
		}
		
		private boolean filter(File file, String name) throws Exception
		{
			String name_ = (String) getName0.t(file);
			return normalize(name_).equals(name);
		}
	}
}
