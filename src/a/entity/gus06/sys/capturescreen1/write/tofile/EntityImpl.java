package a.entity.gus06.sys.capturescreen1.write.tofile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20151021";}
	

	private Service captureImage;
	private Service writeImage;
	
	
	public EntityImpl() throws Exception
	{
		captureImage = Outside.service(this,"gus06.sys.capturescreen1.capture");
		writeImage = Outside.service(this,"gus06.file.write.image.jai.bmp");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file.isDirectory()) throw new Exception("Invalid file: "+file);
		
		Object img = captureImage.g();
		if(img!=null)
		{
			file.getParentFile().mkdirs();
			writeImage.p(new Object[]{file,img});
			return true;
		}
		return false;
	}
}
