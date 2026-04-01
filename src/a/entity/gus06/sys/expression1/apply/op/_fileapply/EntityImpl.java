package a.entity.gus06.sys.expression1.apply.op._fileapply;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220429";}
	
	
	private Service buildFile;
	private Service buildT;
	private Service perform;

	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		buildT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.file.perform.apply.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof File) return new T1((File) value, opMap);
		if(value instanceof String) return new T1(file((String) value, opMap), opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
	
	
	
	private class T1 implements T
	{
		private File file;
		private Map opMap;
		
		public T1(File file, Map opMap)
		{
			this.file = file;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(file,toT(obj));}
		
		private T toT(Object obj) throws Exception
		{return (T) buildT.t(new Object[]{obj,opMap});}
	}
	
	
	private class E1 implements E
	{
		private File file;
		private T t;
		
		public E1(File file, T t)
		{
			this.file = file;
			this.t = t;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{file,t});}
	}
}