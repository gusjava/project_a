package a.entity.gus06.mail.base64decoderstream.image;

import a.framework.*;
import com.sun.mail.util.BASE64DecoderStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240323";}

	private Service isToFile;
	private Service fileToImage;

	public EntityImpl() throws Exception
	{
		isToFile = Outside.service(this,"gus06.io.transfer.tofile");
		fileToImage = Outside.service(this,"gus06.file.read.image.generic");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BASE64DecoderStream content = (BASE64DecoderStream) o[0];
		String ext = (String) o[1];
		
		Object file = isToFile.t(new Object[]{content, "png"});
		return fileToImage.t(file);
	}
}