package a.entity.gus06.dir.op.copy.handler.filtered;

import java.io.File;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service listing;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtopaths");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File in = (File) o[0];
		File out = (File) o[1];
		P fileCopy = (P) o[2];
		F filter = (F) o[3];
		
		if(!in.isDirectory()) throw new Exception("Invalid input directory: "+in);
		
		out.mkdirs();
		int length = in.getAbsolutePath().length();
		List files = (List) listing.t(in);
		
		for(Object f:files)
		{
			File f0 = (File) f;
			if(isValid(f0,filter))
			{
				String part = f0.getAbsolutePath().substring(length);
				File f1 = new File(out,part);
	            
				if(f0.isFile()) copy(f0,f1,fileCopy);
				else f1.mkdirs();
			}
		}
	}
	
	
	private void copy(File f0, File f1, P p) throws Exception
	{p.p(new File[]{f0,f1});}
	
	
	private boolean isValid(File f0, F filter) throws Exception
	{return filter!=null ? filter.f(f0) : true;}
}
