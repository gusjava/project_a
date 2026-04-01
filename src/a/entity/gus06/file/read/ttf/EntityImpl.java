package a.entity.gus06.file.read.ttf;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190927";}


	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		InputStream is = new FileInputStream(file);
		Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		is.close();
		
		return font;
	}
}
