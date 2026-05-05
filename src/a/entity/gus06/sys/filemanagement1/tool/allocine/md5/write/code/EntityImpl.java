package a.entity.gus06.sys.filemanagement1.tool.allocine.md5.write.code;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201006";}
	
	
	private Service writeFile;
	private Service deleteFile;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus.x.file.string.write");
		deleteFile = Outside.service(this,"gus.x.file.op.delete");
		findFile = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.md5.find.file");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		String code = (String) o[2];
		
		File file = (File) findFile.t(new Object[]{engine,md5});
		
		if(code!=null) writeFile.p(new Object[]{file,code});
		else deleteFile.p(file);
	}
}
