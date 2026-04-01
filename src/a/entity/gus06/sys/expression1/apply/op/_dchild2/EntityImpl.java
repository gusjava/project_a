package a.entity.gus06.sys.expression1.apply.op._dchild2;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161214";}
	
	
	private Service buildFile;
	
	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return new T1(toFile((String) value, opMap));
		if(value instanceof File) return new T1(value);
		if(value instanceof File[]) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return build(data,(String) obj);}
	}
	
	
	private Object build(Object obj, String s) throws Exception
	{
		if(obj instanceof File)
		{
			File d = (File) obj;
			return dir2(new File(d,s));
		}
		if(obj instanceof File[])
		{
			File[] d1 = (File[]) obj;
			File[] d2 = new File[d1.length];
			for(int i=0;i<d1.length;i++) d2[i] = dir2(new File(d1[i],s));
			return d2;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File dir2(File file) throws Exception
	{
		if(!file.isDirectory()) throw new Exception("Directory not found at path: "+file);
		return file;
	}
	
	private File toFile(String value, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{value, opMap});}
}