package a.entity.gus06.file.absolute.rebuild;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140710";}

	
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		return new File(rebuild(f));
	}
	
	
	
	private String rebuild(File f)
	{
		StringBuffer b = new StringBuffer();
		int back = 0;
		
		while(f!=null)
		{
			String n = f.getName();
			if(n.equals(".")) {}	
			else if(n.equals("..")) {back++;}
			else
			{
				if(back>0) back--;
				else
				{
					if(n.equals("")) n = f.getAbsolutePath().replace(File.separator,"");
					if(b.length()>0) b.insert(0,File.separator);
					b.insert(0,n);
				}
			}
			
			f = f.getParentFile();
		}
		return b.toString();
	}
}
