package a.entity.gus06.sys.jdbcblob.perform.data.register.prepare;

import a.framework.*;
import java.io.File;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160602";}
	
	public static final long DATA_MAXLENGTH = 200000000L;
	public static final long PREVIEW_MAXLENGTH = 1000000L;


	private Service getName0Ext;
	private Service readFile;
	private Service readImage;
	private Service imageToRawJpg;


	public EntityImpl() throws Exception
	{
		getName0Ext = Outside.service(this,"gus06.file.getname0ext");
		readFile = Outside.service(this,"gus06.file.read.raw");
		readImage = Outside.service(this,"gus06.file.read.image.preview");
		imageToRawJpg = Outside.service(this,"gus06.awt.bufferedimage.tojpg.raw");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return prepareFile((File) obj);
		throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
	
	
	private Object[] prepareFile(File file) throws Exception
	{
		String[] n = (String[]) getName0Ext.t(file);
		String name = n[0];
		String type = n[1];
		
		byte[] content = (byte[]) readFile.t(file);
		if(content.length > DATA_MAXLENGTH)
			throw new Exception("Data is too big: "+content.length);
    	
		BufferedImage previewImage = (BufferedImage) readImage.t(file);
		
		byte[] preview = (byte[])  imageToRawJpg.t(previewImage);
		if(preview.length > PREVIEW_MAXLENGTH)
			throw new Exception("Preview is too big: "+preview.length);

		return new Object[]{name,type,preview,content,previewImage};
	}
}
