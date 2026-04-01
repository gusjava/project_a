package a.entity.gus06.dir.listing0.files.names0;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}

	
	public static final FileFilter FILEFILTER = new FileFilter(){
		public boolean accept(File f) {return f.isFile();}
	};


	private Service getName;

	public EntityImpl() throws Exception
	{getName = Outside.service(this,"gus06.file.getname0");}


	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(dir==null || !dir.isDirectory()) return null;
		
		File[] f = dir.listFiles(FILEFILTER);
		if(f==null) return new String[0];
		
		String[] n = new String[f.length];
		for(int i=0;i<f.length;i++) n[i] = (String) getName.t(f[i]);
		
		return n;
	}
}