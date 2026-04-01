package a.entity.gus06.sys.tesseract1.imagetotext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210607";}


	private Service randomNumber;
	private Service engine;
	private Service writeBmp;
	private File tempDir;


	public EntityImpl() throws Exception
	{
		randomNumber = Outside.service(this,"gus06.data.generate.string.random.number10");
		engine = Outside.service(this,"gus06.sys.tesseract1.engine");
		writeBmp = Outside.service(this,"gus06.file.write.image.bmp");
		tempDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "";
		
		String token = (String) randomNumber.g();
		File imgFile = new File(tempDir,token+".bmp");
		writeBmp.p(new Object[]{imgFile,obj});
		
		String output = (String) engine.t(imgFile);
		imgFile.delete();
		
		return output;
	}
}