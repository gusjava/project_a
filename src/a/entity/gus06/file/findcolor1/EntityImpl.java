package a.entity.gus06.file.findcolor1;

import a.framework.*;
import java.awt.Color;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141209";}


	public static final int LIMIT_DIR = 1000;
	public static final long LIMIT_FILE = 100000000;

	public static final Color COLOR_NORMAL = Color.BLACK;
	public static final Color COLOR_TOO_BIG = Color.GREEN.darker();
	public static final Color COLOR_EMPTY = Color.RED;
	public static final Color COLOR_NOTFOUND = Color.LIGHT_GRAY;



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return COLOR_NOTFOUND;
		return file.isFile()?colorFile(file):colorDir(file);
	}

	
	private Color colorFile(File file)
	{
		long size = file.length();
		if(size==0) return COLOR_EMPTY;
		if(size>LIMIT_FILE) return COLOR_TOO_BIG;
		return COLOR_NORMAL;
	}
	
	
	
	private Color colorDir(File dir)
	{
		if(dir.list().length>LIMIT_DIR) return COLOR_TOO_BIG;
		if(!containsFiles(dir)) return COLOR_EMPTY;
		return COLOR_NORMAL;
	}
	
	
	
	
	private boolean containsFiles(File file)
	{
		File[] c = file.listFiles();
		if(c==null) return false;
		
		int n = c.length;
		if(n==0) return false;
		if(c[0]==null) return false;
		 
		for(int i=0;i<n;i++)
		{
			if(c[i].isFile()) return true;
			if(containsFiles(c[i])) return true;
		}
		return false;
	}
}