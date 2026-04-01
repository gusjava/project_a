package a.entity.gus06.sys.filebackup1.perform.retrieve;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161018";}



	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String timeStamp = ""+o[1];
		
		if(timeStamp.equals("first")) return first(dir);
		if(timeStamp.equals("last")) return last(dir);
		
		try
		{
			int index = Integer.parseInt(timeStamp);
			return fileAtIndex(dir,index);
		}
		catch(NumberFormatException e){}
		
		File[] ff = dir.listFiles();
		for(File f:ff) if(f.getName().startsWith(timeStamp+"_")) return f;
		
		return null;
	}
	
	
	private File first(File dir)
	{
		File[] ff = dir.listFiles();
		if(ff==null || ff.length==0) return null;
		return ff[0];
	}
	
	private File last(File dir)
	{
		File[] ff = dir.listFiles();
		if(ff==null || ff.length==0) return null;
		return ff[ff.length-1];
	}
	
	private File fileAtIndex(File dir, int index)
	{
		File[] ff = dir.listFiles();
		int size = ff!=null ? ff.length : 0;
		
		if(size==0) return null;
		while(index<0) index += size;
		return index>=size ? null : ff[index];
	}
	
	
	private boolean isInt(String s) throws Exception
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e){return false;}
	}
}
