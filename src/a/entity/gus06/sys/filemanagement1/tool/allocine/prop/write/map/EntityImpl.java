package a.entity.gus06.sys.filemanagement1.tool.allocine.prop.write.map;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201006";}
	
	
	private Service writeProp;
	private Service findFile;
	private Service checkFormat;

	public EntityImpl() throws Exception
	{
		writeProp = Outside.service(this,"gus06.file.write.properties");
		findFile = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.prop.find.file");
		checkFormat = Outside.service(this,"gus06.web.allocine.convert.query1todata1.checkformat");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String code = (String) o[1];
		Map prop = (Map) o[2];
		
		if(!checkFormat.f(prop)) throw new Exception("Invalid movie map format");
		
		File file = (File) findFile.t(new Object[]{engine,code});
		writeProp.p(new Object[]{file,prop});
	}
}
