package a.entity.gus06.dir.runtask.zip_gus.build;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231126";}
	
	public static final String INITFILE = "init.gus";
	public static final String EXT = "zip_gus";
	


	private Service runZip;
	
	public EntityImpl() throws Exception
	{runZip = Outside.service(this,"gus06.file.zip.run.zip");}
	

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File initFile = new File(dir,INITFILE);
		if(!initFile.isFile()) throw new Exception("Init gus script not found: "+initFile);
		if(initFile.length()==0) throw new Exception("Init gus script is empty: "+initFile);
		
		File zipGus = new File(dir.getAbsolutePath()+"."+EXT);
		
		File[] ff = dir.listFiles();
		runZip.p(new Object[]{ff,zipGus,progress,interrupt});
	}
}