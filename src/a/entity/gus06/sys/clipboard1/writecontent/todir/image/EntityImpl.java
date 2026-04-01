package a.entity.gus06.sys.clipboard1.writecontent.todir.image;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151021";}


	private Service buildFile;
	private Service writeFileBmp;
	private Service writeFilePng;
	private Service checkBmpCompa;


	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.buildfile");
		writeFileBmp = Outside.service(this,"gus06.file.write.image.jai.bmp");
		writeFilePng = Outside.service(this,"gus06.file.write.image.png");
		checkBmpCompa = Outside.service(this,"gus06.image.check.compatible.bmp");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object image = o[1];
		
		dir.mkdirs();
		
		if(checkBmpCompa.f(image))
		{
			File file = (File) buildFile.t(new Object[]{dir,"bmp"});
			writeFileBmp.p(new Object[]{file,image});
		}
		else
		{
			File file = (File) buildFile.t(new Object[]{dir,"png"});
			writeFilePng.p(new Object[]{file,image});
		}
	}
}
