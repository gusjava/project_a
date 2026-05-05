package a.entity.gus06.file.runtask.zip;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150629";}


	private Service runZip;
	private Service getName;

	public EntityImpl() throws Exception
	{
		runZip = Outside.service(this,"gus06.file.zip.run.zip");
		getName = Outside.service(this,"gus.x.file.getname0");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File parent = file.getParentFile();
		String name = (String) getName.t(file);
		
		File zip = new File(parent,name+".zip");
		if(zip.equals(file)) zip = new File(zip.getAbsolutePath()+".zip");
		
		runZip.p(new Object[]{file,zip,progress,interrupt});
	}
}
