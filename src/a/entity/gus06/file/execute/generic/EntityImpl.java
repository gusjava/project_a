package a.entity.gus06.file.execute.generic;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200310";}


	private Service isGusFile;
	private Service openFile;
	private Service executeScript;

	public EntityImpl() throws Exception
	{
		isGusFile = Outside.service(this,"gus06.file.filter.ext.istype.text.gus");
		openFile = Outside.service(this,"gus06.awt.desktop.open");
		executeScript = Outside.service(this,"gus06.file.string.perform.execute.script1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(isGusFile.f(file)) executeScript.p(file);
		else openFile.p(file);
	}
}
