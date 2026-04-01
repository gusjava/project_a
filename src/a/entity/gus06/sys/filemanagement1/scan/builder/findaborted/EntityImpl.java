package a.entity.gus06.sys.filemanagement1.scan.builder.findaborted;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191204";}


	private Service confirm;
	private Service emptyDir;
	
	public EntityImpl() throws Exception
	{
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File runningDir = (File) obj;
		
		File[] kk = runningDir.listFiles();
		if(kk==null) return null;
		if(kk.length==0) return null;
		
		File k = findMaxSize(kk);
		
		boolean resume = confirm.f("Previous scan have been aborted: "+k.getName()+"\nResume ?");
		if(resume) return k;
		
		emptyDir.p(runningDir);
		return null;
	}
	
	
	
	
	private File findMaxSize(File[] kk)
	{
		File k1 = kk[0];
		long size1 = k1.length();
		
		for(File k : kk) if(k.length()>size1)
		{
			k1 = k;
			size1 = k.length();
		}
		return k1;
	}
}
