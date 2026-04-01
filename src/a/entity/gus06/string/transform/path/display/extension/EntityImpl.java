package a.entity.gus06.string.transform.path.display.extension;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141113";}


	private Service extension;

	public EntityImpl() throws Exception
	{extension = Outside.service(this,"gus06.file.getextension");}

	
	
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
			if(file.isFile())
				b.append(extension(file)+"\t"+file+"\n");
			else b.append(line+"\n");
		}
		catch(Exception e)
		{b.append(line+"\n");}
	}
	
	
	private String extension(File file) throws Exception
	{return (String) extension.t(file);}
}
