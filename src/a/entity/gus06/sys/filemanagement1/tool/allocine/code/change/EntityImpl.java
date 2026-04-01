package a.entity.gus06.sys.filemanagement1.tool.allocine.code.change;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201020";}

	public static final String KEY_CODE = "allocine.code";
	

	private Service putField;
	private Service writeCode;

	public EntityImpl() throws Exception
	{
		putField = Outside.service(this,"gus06.file.properties.perform.field.put");
		writeCode = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.md5.write.code");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		String newCode = (String) o[2];
		
		if(newCode.equals("")) newCode = null;
		
		File propFile = (File) ((R) engine).r("propFile:"+md5);
		boolean updated = putField.f(new Object[]{propFile,KEY_CODE,newCode});
		if(updated) writeCode.p(new Object[]{engine,md5,newCode});
		
		return updated;
	}
}
