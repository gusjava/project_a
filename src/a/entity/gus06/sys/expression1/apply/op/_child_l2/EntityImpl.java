package a.entity.gus06.sys.expression1.apply.op._child_l2;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}
	
	public static final int LEVEL = 2;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return child((File) obj,LEVEL);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private File child(File f, int level)
	{
		for(int i=0;i<level;i++)
		f = nextChild(f);
		return f;
	}
	
	
	private File nextChild(File dir)
	{
		if(dir==null) return null;
		if(!dir.isDirectory()) return null;
		File[] ff = dir.listFiles();
		
		if(ff==null) return null;
		if(ff.length!=1) return null;
		
		return ff[0];
	}
}
