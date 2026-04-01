package a.entity.gus06.string.transform.path.display.infos;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250503";}

	
	
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
			File file = new File(line.trim());
			b.append(info(file)+"\t"+file+"\n");
		}
		catch(Exception e)
		{b.append(line+"\n");}
	}
	
	private String info(File file)
	{
		if(!file.exists()) return "NOT_FOUND";
		if(file.isFile()) return "FILE";
		if(file.isDirectory()) return "DIR";
		return "?";
	}
}