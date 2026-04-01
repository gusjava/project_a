package a.entity.gus06.file.dll.loadlibrary.fromdir;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150607";}

	
	public static final FileFilter DLL_FILTER = new FileFilter(){
		public boolean accept(File f) {
			return f.isFile() && f.getName().toLowerCase().endsWith(".dll");
		}
	};


	private Service loadDll;

	public EntityImpl() throws Exception
	{
		loadDll = Outside.service(this,"gus06.file.dll.loadlibrary.fromfile");
	}



	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		File[] dll = dir.listFiles(DLL_FILTER);
		if(dll!=null) for(int i=0;i<dll.length;i++) loadDll(dll[i]);
	}
	
	
	
	private void loadDll(File dll)
	{
		try{loadDll.p(dll);}
		catch(Exception e)
		{Outside.err(this,"loadDll(File)",e);}
	}
}
