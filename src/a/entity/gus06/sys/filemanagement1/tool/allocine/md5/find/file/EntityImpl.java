package a.entity.gus06.sys.filemanagement1.tool.allocine.md5.find.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201006";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		
		File dir = (File) ((R)engine).r("dirAllocine");
		File md5Dir = new File(dir,"md5_code");
		md5Dir.mkdirs();
		
		return new File(md5Dir,md5+".txt");
	}
}
