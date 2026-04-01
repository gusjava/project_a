package a.entity.gus06.data.perform.exec.asbatch;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230207";}


	private Service prepareFile;
	private Service execFile;

	public EntityImpl() throws Exception
	{
		prepareFile = Outside.service(this,"gus06.data.perform.exec.asbatch.prepare");
		execFile = Outside.service(this,"gus06.runtime.exec.file");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) prepareFile.t(obj);
		return execFile.t(file);
	}
}