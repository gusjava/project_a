package a.entity.gus06.dir.listing.dirtofiles.set.extension;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150629";}


	private Service getExtension;

	public EntityImpl() throws Exception
	{getExtension = Outside.service(this,"gus06.file.getextension");}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Set set = new HashSet();
    		handleDir(set,dir);
        	return set;
	}
	
	
	private void handleDir(Set set, File dir) throws Exception
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(set,f);
			else set.add(getExtension(f));
		}
	}
	
	private String getExtension(File f) throws Exception
	{return (String) getExtension.t(f);}
}
