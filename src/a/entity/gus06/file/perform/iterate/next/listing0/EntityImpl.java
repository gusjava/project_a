package a.entity.gus06.file.perform.iterate.next.listing0;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return next((File) obj);
	}
	
	
	private File next(File f) throws Exception
	{
		File[] ff = f.getParentFile().listFiles();
		int index = indexOf(ff,f);
		if(index==-1) throw new Exception("File not found: "+f);
		
		if(index==ff.length-1) return null;
		return ff[index+1];
	}
	
	
	
	private int indexOf(File[] ff, File f)
	{
		for(int i=0;i<ff.length;i++)
		if(ff[i].equals(f)) return i;
		return -1;
	}
}
