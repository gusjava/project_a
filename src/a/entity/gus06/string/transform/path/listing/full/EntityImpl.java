package a.entity.gus06.string.transform.path.listing.full;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141113";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++) handle(b,n[i]);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private void handle(StringBuffer b, String line)
	{
		try
		{
			String lineStrim = line.trim();
			File dir = new File(lineStrim);
			if(dir.isDirectory() && !lineStrim.equals(""))
				handle(b,dir);
			else b.append(line+"\n");
		}
		catch(Exception e)
		{b.append(line+"\n");}
	}
	
	
	private void handle(StringBuffer b, File dir)
	{
		b.append(dir.getAbsolutePath()+"\n");
		File[] children = dir.listFiles();
		if(children==null) return;
        
		for(int i=0;i<children.length;i++)
		handle(b,children[i]);
	}
}