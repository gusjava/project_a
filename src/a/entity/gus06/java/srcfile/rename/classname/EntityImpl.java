package a.entity.gus06.java.srcfile.rename.classname;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}


	private Service readFile;
	private Service handleSrc;
	private Service writeFile;
	private Service deleteFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		handleSrc = Outside.service(this,"gus06.java.srccode.rename.classname");
		writeFile = Outside.service(this,"gus06.file.write.string.charsetfrom");
		deleteFile = Outside.service(this,"gus.x.file.op.delete");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String newName = (String) o[1];
		
		String src = (String) readFile.t(file);
		String src1 = (String) handleSrc.t(new String[]{src,newName});
		
		File file1 = new File(file.getParentFile(),newName+".java");
		writeFile.p(new Object[]{file1,file,src1});
		deleteFile.p(file);
		
		return file1;
	}
}
