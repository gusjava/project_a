package a.entity.gus06.sys.capturescreen1.write.todir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20151021";}
	

	private Service captureImage;
	private Service writeImage;
	
	
	public EntityImpl() throws Exception
	{
		captureImage = Outside.service(this,"gus06.sys.capturescreen1.capture");
		writeImage = Outside.service(this,"gus06.sys.clipboard1.writecontent.todir.image");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(dir.isFile()) throw new Exception("Invalid dir: "+dir);
		
		Object img = captureImage.g();
		if(img!=null)
		{
			writeImage.p(new Object[]{dir,img});
			return true;
		}
		return false;
	}
}
