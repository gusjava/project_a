package a.entity.gus06.dir.listing0.names0.aslist;

import java.io.File;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230219";}


	private Service getName;

	public EntityImpl() throws Exception
	{getName = Outside.service(this,"gus.x.file.getname0");}


	public Object t(Object obj) throws Exception
	{
		File[] f = toListing(obj);
		if(f==null) return new ArrayList();
		
		List list = new ArrayList();
		for(int i=0;i<f.length;i++) list.add((String) getName.t(f[i]));
		
		return list;
	}
	
	
	
	private File[] toListing(Object obj) throws Exception
	{
		if(obj instanceof File[]) return (File[]) obj;
		if(obj instanceof File) return ((File) obj).listFiles();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}