package a.entity.gus06.sys.clipboard1.writecontent.todir.string;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151021";}


	private Service buildFile;
	private Service writeFile;
	private Service isJavaSrc;
	private Service handleJavaSrc;


	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.buildfile");
		writeFile = Outside.service(this,"gus06.file.write.string.autodetect");
		isJavaSrc = Outside.service(this,"gus06.java.srccode.isvalid");
		handleJavaSrc = Outside.service(this,"gus06.sys.clipboard1.writecontent.todir.javasrc");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String txt = (String) o[1];
		
		if(isJavaSrc.f(txt))
		{
			handleJavaSrc.p(obj);
			return;
		}
		
		File file = (File) buildFile.t(new Object[]{dir,"txt"});
		dir.mkdirs();
		writeFile.p(new Object[]{file,txt});
	}
}
