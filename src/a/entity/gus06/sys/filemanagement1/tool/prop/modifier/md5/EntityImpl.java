package a.entity.gus06.sys.filemanagement1.tool.prop.modifier.md5;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20210108";}


	private Service input;
	private Service putField;

	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog.change");
		putField = Outside.service(this,"gus06.file.properties.perform.field.put");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String propName = (String) o[1];
		String valueOld = (String) o[2];
		String md5 = (String) o[3];
		
		String valueNew = (String) input.t(new String[]{"Enter new value:",valueOld});
		if(valueNew==null) return false;
		
		File infoFile = (File) ((R)engine).r("infoFile:"+md5);
		putField.p(new Object[]{infoFile,propName,valueNew});
		
		return true;
	}
}