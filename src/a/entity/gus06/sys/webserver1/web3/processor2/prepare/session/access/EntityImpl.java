package a.entity.gus06.sys.webserver1.web3.processor2.prepare.session.access;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, R, P {

	public String creationDate() {return "20160219";}


	private Service removeDir;
	private Service emptyDir;
	private Service accessBuilder;
	private Service formatFileName;
	
	private File storeDir;
	
	

	public EntityImpl() throws Exception
	{
		removeDir = Outside.service(this,"gus06.dirfile.perform.remove");
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
		accessBuilder = Outside.service(this,"gus06.app.persister1.builder");
		formatFileName = Outside.service(this,"gus06.string.transform.normalize.filename");
		
		storeDir = (File) Outside.resource(this,"path#path.web3.dir.sessions");
		if(storeDir.exists()) emptyDir.p(storeDir);
	}
	
	
	
	public synchronized void p(Object obj) throws Exception
	{
		String id = (String) obj;
		File sessionDir = new File(storeDir,dirName(id));
		if(sessionDir.exists()) removeDir.p(sessionDir);
	}
	
	
	public synchronized Object r(String key) throws Exception
	{
		File sessionDir = new File(storeDir,dirName(key));
		if(!sessionDir.exists()) sessionDir.mkdirs();
		return accessBuilder.t(sessionDir);
	}
	
	private String dirName(String s) throws Exception
	{return (String) formatFileName.t(s);}
}
