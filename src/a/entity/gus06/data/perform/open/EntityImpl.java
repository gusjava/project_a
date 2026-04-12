package a.entity.gus06.data.perform.open;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160819";}

	private Service openFile;
	
	public EntityImpl() throws Exception
	{
		openFile = Outside.service(this,"gus06.awt.desktop.open");
	}

	public void p(Object obj) throws Exception
	{
		if(obj instanceof File)
		{open((File) obj);return;}
		
		if(obj instanceof File[])
		{open((File[]) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void open(File f) throws Exception
	{
		openFile.p(f);
	}
	
	private void open(File[] ff) throws Exception
	{
		for(File f:ff)
		openFile.p(f);
	}
}
