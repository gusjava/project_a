package a.entity.gus06.sys.filemanagement1.tool.allocine.gen.code_md5.find.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201108";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String code = (String) o[1];
		
		File dir0 = (File) ((R)engine).r("dirGenerated_allocine");
		File dir1 = new File(dir0,"code_md5");
		dir1.mkdirs();
		
		return new File(dir1,code+".txt");
	}
}