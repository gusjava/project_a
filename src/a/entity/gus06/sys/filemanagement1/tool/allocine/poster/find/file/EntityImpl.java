package a.entity.gus06.sys.filemanagement1.tool.allocine.poster.find.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201004";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String code = (String) o[1];
		
		if(code==null) return null;
		
		File dir = (File) ((R)engine).r("dirAllocine");
		File posterDir = new File(dir,"code_poster");
		posterDir.mkdirs();
		
		return new File(posterDir,code+".jpg");
	}
}
