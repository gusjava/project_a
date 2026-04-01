package a.entity.gus06.data.perform.open.asbatch;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230215";}


	private Service prepareFile;
	private Service openFile;

	public EntityImpl() throws Exception
	{
		prepareFile = Outside.service(this,"gus06.data.perform.exec.asbatch.prepare");
		openFile = Outside.service(this,"gus06.awt.desktop.open");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) prepareFile.t(obj);
		return openFile.t(file);
	}
}