package a.entity.gus06.dir.runtask.sendfiles.blobdata.jdbc;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170128";}


	private Service listing;
	private Service input;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.files");
		input = Outside.service(this,"gus06.input.textarea.dialog");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		String infos = (String) input.t("Please, provide JDBC connection information:");
		if(infos==null) return;
		
		File dirDone = new File(dir.getAbsolutePath()+"_DONE");
		File dirFailed = new File(dir.getAbsolutePath()+"_FAILED");
		
		dirDone.mkdirs();
		dirFailed.mkdirs();
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(int i=0;i<ff.length;i++)
		{
			File f = ff[i];
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
