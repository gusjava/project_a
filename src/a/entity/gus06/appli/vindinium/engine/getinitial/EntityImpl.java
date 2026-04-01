package a.entity.gus06.appli.vindinium.engine.getinitial;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170923";}


	private Service readFile;
	private Service parseJson;
	private Service findFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		parseJson = Outside.service(this,"gus06.file.convert.json.parser");
		findFile = Outside.service(this,"gus06.appli.vindinium.engine.getinitial.findfile");
	}

	public Object g() throws Exception
	{
		File file = (File) findFile.g();
		String text = (String) readFile.t(file);
		return parseJson.t(text);
	}
}
