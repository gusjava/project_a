package a.entity.gus06.file.read.image.from.txt;

import java.awt.image.BufferedImage;
import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}

	
	private Service readTextFile;
	private Service textToImage;
	
	
	public EntityImpl() throws Exception
	{
		readTextFile = Outside.service(this,"gus06.file.read.string.autodetect");
		textToImage = Outside.service(this,"gus06.awt.bufferedimage.build.fromtext1");
	}
	


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return null;
		
		String text = readTextFile(file);
		return textToImage(text);
	}
	
	
	
	private String readTextFile(File file) throws Exception
	{return (String) readTextFile.t(file);}
	
	
	private BufferedImage textToImage(String text) throws Exception
	{return (BufferedImage) textToImage.t(text);}
}
