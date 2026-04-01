package a.entity.gus06.sys.clipboard1.writecontent.todir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20151021";}
	

	private Service accessImage;
	private Service accessString;
	
	private Service writeImage;
	private Service writeString;
	
	
	public EntityImpl() throws Exception
	{
		accessImage = Outside.service(this,"gus06.clipboard.access.image");
		accessString = Outside.service(this,"gus06.clipboard.access.string");
		
		writeImage = Outside.service(this,"gus06.sys.clipboard1.writecontent.todir.image");
		writeString = Outside.service(this,"gus06.sys.clipboard1.writecontent.todir.string");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(dir.isFile()) throw new Exception("Invalid dir: "+dir);
		
		Object img = accessImage.g();
		if(img!=null)
		{
			writeImage.p(new Object[]{dir,img});
			return true;
		}
		
		Object string = accessString.g();
		if(string!=null)
		{
			writeString.p(new Object[]{dir,string});
			return true;
		}
		
		return false;
	}
}
