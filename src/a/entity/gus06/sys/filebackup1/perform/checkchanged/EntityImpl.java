package a.entity.gus06.sys.filebackup1.perform.checkchanged;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190724";}


	private Service checkDoubloon;

	public EntityImpl() throws Exception
	{
		checkDoubloon = Outside.service(this,"gus06.file.doubloon.check");
	}

	
	public boolean f(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = o[0];
		File file = o[1];
		
		File lastFile = last(dir);
		if(lastFile==null) return true;
		
		return !checkDoubloon.f(new File[]{file,lastFile});
	}
	
	private File last(File dir)
	{
		File[] ff = dir.listFiles();
		if(ff==null || ff.length==0) return null;
		return ff[ff.length-1];
	}
}
