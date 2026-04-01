package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_c.copy.perform2.copyfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220603";}


	private Service copyFile;


	public EntityImpl() throws Exception
	{
		copyFile = Outside.service(this,"gus06.clipboard.access.file");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String line = (String) obj;
		try
		{
			File f = getFile(line);
			if(f==null) return false;
			copyFile.p(f);
			return true;
		}
		catch(Exception e)
		{return false;}
	}
	
	
	
	
	private File getFile(String line)
	{
		File f = new File(line);
		if(f.exists()) return f;
		
		if(line.length()>2 && line.charAt(1)==':')
		{
			line = line.substring(2);
			File[] rr = File.listRoots();
			for(File r:rr)
			{
				f = new File(r+line);
				if(f.exists()) return f;
			}
		}
		return null;
	}
}