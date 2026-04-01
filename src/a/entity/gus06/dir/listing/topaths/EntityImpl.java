package a.entity.gus06.dir.listing.topaths;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150605";}

	
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList();
		
		if(obj instanceof File) handlePath(list,(File) obj);
		else if(obj instanceof File[]) handleArray(list,(File[]) obj);
		else if(obj instanceof List) handleList(list,(List) obj);
		else if(obj instanceof Set) handleSet(list,(Set) obj);
		
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
		
        	return list;
	}
	
	
	private void handlePath(List list, File path)
	{
		list.add(path);
		if(path.isDirectory()) handleDirContent(list,path);
	}
	
	private void handleDirContent(List list, File dir)
	{
		File[] ff = dir.listFiles();
		for(File f:ff) handlePath(list,f);
	}
	
	private void handleArray(List list, File[] ff)
	{
		for(File f:ff) handlePath(list,f);
	}
	
	private void handleList(List list, List l)
	{
		for(Object o:l) handlePath(list,(File)o);
	}
	
	private void handleSet(List list, Set s)
	{
		for(Object o:s) handlePath(list,(File)o);
	}
}
