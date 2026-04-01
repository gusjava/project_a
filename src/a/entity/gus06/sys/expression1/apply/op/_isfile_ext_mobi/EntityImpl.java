package a.entity.gus06.sys.expression1.apply.op._isfile_ext_mobi;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}

	public static final String EXTENSION = "mobi";
	

	private Service getExt;

	public EntityImpl() throws Exception
	{
		getExt = Outside.service(this,"gus06.file.getextension.lowercase");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof File)) return Boolean.FALSE;
		
		File file = (File) obj;
		if(!file.isFile()) return Boolean.FALSE;
		
		String ext = (String) getExt.t(file);
		return Boolean.valueOf(ext.equals(EXTENSION));
	}
}