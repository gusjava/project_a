package a.entity.gus06.sys.filemanagement1.tool.allocine.poster.write.image;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201006";}
	
	
	private Service writeImage;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		writeImage = Outside.service(this,"gus06.file.write.image.jpg");
		findFile = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.poster.find.file");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String code = (String) o[1];
		Object image = o[2];
		
		File file = (File) findFile.t(new Object[]{engine,code});
		writeImage.p(new Object[]{file,image});
	}
}
