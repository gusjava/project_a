package a.entity.gus06.sys.expression1.apply.op._isfile_ext;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170211";}
	

	private Service getExt;
	private Service listToStringArray;

	public EntityImpl() throws Exception
	{
		getExt = Outside.service(this,"gus06.file.getextension.lowercase");
		listToStringArray = Outside.service(this,"gus06.convert.listtostringarray");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Filter(obj);
	}
	
	
	
	private class Filter implements F
	{
		private Object data;
		public Filter(Object data) {this.data = data;}
		
		public boolean f(Object obj) throws Exception
		{
			if(data==null) return false;
			if(!(data instanceof File)) return false;
			
			File file = (File) data;
			if(!file.isFile()) return false;
			
			String ext = (String) getExt.t(file);
			
			String[] nn = findExtensions(obj);
			for(String n:nn) if(ext.equals(n)) return true;
			return false;
		}
	}
	
	private String[] findExtensions(Object obj) throws Exception
	{
		if(obj==null) return new String[0];
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof List) return (String[]) listToStringArray.t(obj);
		if(obj instanceof String) return ((String) obj).split("\\|");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
