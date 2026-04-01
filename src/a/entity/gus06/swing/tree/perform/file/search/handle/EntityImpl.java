package a.entity.gus06.swing.tree.perform.file.search.handle;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220208";}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map search = (Map) o[0];
		File root = (File) o[1];
		F filter = (F) o[2];
		
		handleFile(root,filter,search);
	}
	
	
	
	private int handleFile(File file, F filter, Map search) throws Exception
	{
		boolean fit = filter.f(file);
		
		if(file.isDirectory())
		{
			int total = 0;
			File[] ff = file.listFiles();
			if(ff!=null) for(File f : ff)
			total += handleFile(f,filter,search);
			
			if(!fit && total==0) return 0;
			
			if(fit)
			{
				search.put(file.getAbsolutePath(),total+"*");
				return total+1;
			}
			search.put(file.getAbsolutePath(),""+total);
			return total;
		}
		if(file.isFile())
		{
			if(fit)
			{
				search.put(file.getAbsolutePath(),"*");
				return 1;
			}
		}
		return 0;
	}
}