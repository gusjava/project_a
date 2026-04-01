package a.entity.gus06.file.findcolor1.filetomark;

import a.framework.*;
import java.awt.Color;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220517";}


	public static final int LIMIT_DIR = 1000;
	public static final long LIMIT_FILE = 100000000;

	public static final String MARK_NORMAL = ">";
	public static final String MARK_TOO_BIG = "!";
	public static final String MARK_EMPTY = "&";
	public static final String MARK_NOTFOUND = "*";



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return MARK_NOTFOUND;
		return file.isFile()?handleFile(file):handleDir(file);
	}

	
	private String handleFile(File file)
	{
		long size = file.length();
		if(size==0) return MARK_EMPTY;
		if(size>LIMIT_FILE) return MARK_TOO_BIG;
		return MARK_NORMAL;
	}
	
	
	
	private String handleDir(File dir)
	{
		if(dir.list().length>LIMIT_DIR) return MARK_TOO_BIG;
		if(!containsFiles(dir)) return MARK_EMPTY;
		return MARK_NORMAL;
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