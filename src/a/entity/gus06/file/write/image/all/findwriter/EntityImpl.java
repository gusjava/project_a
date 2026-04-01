package a.entity.gus06.file.write.image.all.findwriter;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}

	
	private Service writeBmp;
	private Service writeWbmp;
	private Service writeJpg;
	private Service writeGif;
	private Service writePng;
	private Service writeTiff;
	private Service writeIco;
	
	private Service getExtension;
	
	
	
	public EntityImpl() throws Exception
	{
		writeBmp = Outside.service(this,"gus06.file.write.image.bmp");
		writeWbmp = Outside.service(this,"gus06.file.write.image.wbmp");
		writeJpg = Outside.service(this,"gus06.file.write.image.jpg");
		writeGif = Outside.service(this,"gus06.file.write.image.gif");
		writePng = Outside.service(this,"gus06.file.write.image.png");
		writeTiff = Outside.service(this,"gus06.file.write.image.tiff");
		writeIco = Outside.service(this,"gus06.file.write.ico");
		
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
	}
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String ext = (String) getExtension.t(file);
		
		if(ext.equals("bmp")) return writeBmp;
		if(ext.equals("wbmp")) return writeWbmp;
		if(ext.equals("jpg")) return writeJpg;
		if(ext.equals("jpeg")) return writeJpg;
		if(ext.equals("gif")) return writeGif;
		if(ext.equals("png")) return writePng;
		if(ext.equals("tiff")) return writeTiff;
		if(ext.equals("ico")) return writeIco;
		
		throw new Exception("Writer not found for file "+file.getName());
	}
}
