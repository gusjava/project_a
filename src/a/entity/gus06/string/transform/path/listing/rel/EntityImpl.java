package a.entity.gus06.string.transform.path.listing.rel;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240620";}

	
	
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
			File f = new File(lineStrim);
			if(f.isDirectory() && !lineStrim.equals(""))
				handle(b,f);
			else b.append(line+"\n");
		}
		catch(Exception e)
		{b.append(line+"\n");}
	}
	
	
	private void handle(StringBuffer b, File f)
	{
		b.append(f.getAbsolutePath()+"\n");
		int len = f.getAbsolutePath().length()+1;
		File[] children = f.listFiles();
		if(children==null) return;
        
		for(int i=0;i<children.length;i++)
		handle(b,children[i],len);
	}
	
	private void handle(StringBuffer b, File f, int len)
	{
		String path = f.getAbsolutePath().substring(len);
		b.append(path+"\n");
		File[] children = f.listFiles();
		if(children==null) return;
        
		for(int i=0;i<children.length;i++)
		handle(b,children[i],len);
	}
}