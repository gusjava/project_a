package a.entity.gus06.appli.entityhistory.map.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20150430";}

	public static final String FILENAME = "entitydates.properties";


	private Service readFile;
	private Service writeFile;
	
	private Map map;
	
	private File storeDir;
	private File propFile;
	

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
		propFile = new File(storeDir,FILENAME);
		
		if(propFile.exists())
		map = (Map) readFile.t(propFile);
	}
	
	
	public Object g() throws Exception
	{return map;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		writeFile.p(new Object[]{propFile,map});
		mapModified();
	}
	
	
	
	private void mapModified()
	{send(this,"mapModified()");}
}
