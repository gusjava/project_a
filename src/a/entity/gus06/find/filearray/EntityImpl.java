package a.entity.gus06.find.filearray;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}
	
	
	public Object t(Object obj) throws Exception
	{return toArray(obj);}
	
	
	
	
	private File[] toArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File[]) return (File[]) obj;
		if(obj instanceof Object[]) return toArray((Object[]) obj);
		if(obj instanceof List) return toArray((List) obj);
		if(obj instanceof File) return toArray((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File[] toArray(Object[] array)
	{
		File[] files = new File[array.length];
		for(int i=0;i<array.length;i++) files[i] = (File) array[i];
		return files;
	}
	
	private File[] toArray(List list)
	{
		File[] files = new File[list.size()];
		for(int i=0;i<list.size();i++) files[i] = (File) list.get(i);
		return files;
	}
	
	private File[] toArray(File f)
	{
		return new File[]{f};
	}
}