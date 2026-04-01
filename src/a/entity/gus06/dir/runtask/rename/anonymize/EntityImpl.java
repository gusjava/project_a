package a.entity.gus06.dir.runtask.rename.anonymize;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150620";}


	private Service listing;
	private Service getExtension;
	private Service randomId;
	
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0");
		getExtension = Outside.service(this,"gus06.file.getextension");
		randomId = Outside.service(this,"gus06.data.generate.string.random.number14");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			String ext = (String) getExtension.t(f);
			String id = (String) randomId.g();
			String newName = id+"."+ext;
			
			File f1 = new File(dir,newName);
			rename(f,f1);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
}
