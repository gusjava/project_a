package a.entity.gus06.dir.runtask0.classify.perform;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180218";}


	private Service listing;
	private Service moveFile;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		moveFile = Outside.service(this,"gus06.file.op.move.autorename");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		T t = (T) o[3];
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			handleFile(dir,f,t);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private void handleFile(File dir, File f, T t)
	{
		try
		{
			String info = findInfo(f,t);
			
			File d = new File(dir,info);
			File f1 = new File(d,f.getName());
			
			moveFile.p(new File[]{f,f1});
		}
		catch(Exception e)
		{Outside.err(this,"handleFile(File,File,T)",e);}
	}
	
	
	private String findInfo(File f, T t)
	{
		try
		{
			String info = (String) t.t(f);
			return info==null ? "NULL" : info;
		}
		catch(Exception e)
		{Outside.err(this,"findInfo(File,T)",e);}
		return "ERROR";
	}
}
