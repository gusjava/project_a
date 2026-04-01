package a.entity.gus06.file.read.image.bmp.impl1;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20151001";}


	private BmpBuilder_2 builder;
	

	public Object t(Object obj) throws Exception
	{
		File file = (File)obj;
		builder = new BmpBuilder_2(file);
		return builder.image;
	}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(builder==null) throw new Exception("No bmp has been built yet");
		if(key.equals("info")) return builder.toString();
		if(key.equals("palette")) return builder.npalette;

		throw new Exception("Unknown key: "+key);
	}


}
