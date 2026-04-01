package a.entity.gus06.data.perform.delete.ifempty;

import a.framework.*;
import java.io.File;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220612";}


	private Service deleteFile;
	
	
	public EntityImpl() throws Exception
	{
		deleteFile = Outside.service(this,"gus06.dirfile.op.delete.ifempty");
	}


	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof File)
		{delete((File) obj);return;}
		
		if(obj instanceof File[])
		{delete((File[]) obj);return;}
		
		if(obj instanceof Iterator)
		{delete((Iterator) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void delete(File f) throws Exception
	{
		deleteFile.p(f);
	}
	
	private void delete(File[] ff) throws Exception
	{
		for(File f:ff)
		deleteFile.p(f);
	}
	
	private void delete(Iterator it)
	{
		it.remove();
	}
}