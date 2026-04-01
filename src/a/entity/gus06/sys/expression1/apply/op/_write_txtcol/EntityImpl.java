package a.entity.gus06.sys.expression1.apply.op._write_txtcol;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220617";}
	


	private Service writeList;
	private Service writeSet;
	
	public EntityImpl() throws Exception
	{
		writeList = Outside.service(this,"gus06.file.write.string.list.cs.default0");
		writeSet = Outside.service(this,"gus06.file.write.string.set.cs.default0");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new T1((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private File file;
		public T1(File file) {this.file = file;}
		
		public Object t(Object obj) throws Exception
		{return new E1(file,obj);}
	}
	
	
	private class E1 implements E
	{
		private File file;
		private Object data;
		
		public E1(File file, Object data) throws Exception
		{
			this.file = file;
			this.data = data;
		}
		
		public void e() throws Exception
		{
			if(data instanceof List) writeList.p(new Object[]{file,data});
			else if(data instanceof Set) writeList.p(new Object[]{file,data});
			else throw new Exception("Unsupported data type: "+data.getClass().getName());
		}
	}
}
