package a.entity.gus06.file.newfile.autorename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140918";}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return file;
		
		String ext = getExtension(file);
		String path = file.getAbsolutePath();
		
		if(ext.equals(""))
		{
			int n = 0;
			while(file.exists())
			{n++;file = new File(path+"_"+n);}
			return file;
		}
		
		String path0 = path.substring(0,path.length()-ext.length()-1);
		int n = 0;
		while(file.exists())
		{n++;file = new File(path0+"_"+n+"."+ext);}
		return file;
	}
	
	
	
	
	private String getExtension(File f)
	{
		String name = f.getName();
		if(!name.contains(".")) return "";
		String[] n = name.split("\\.");
		return n[n.length-1];
	}
}
