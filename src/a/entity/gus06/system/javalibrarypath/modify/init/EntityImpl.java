package a.entity.gus06.system.javalibrarypath.modify.init;

import java.io.File;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20150607";}

	public static final String KEY_SYSTEMLIBRARYPATH = "system.librarypath";

	
	private Service modifyPath;
	private Service fp;
	private Map prop;
	

	public EntityImpl() throws Exception
	{
		modifyPath = Outside.service(this,"gus06.system.javalibrarypath.modify");
		fp = Outside.service(this,"fileprovider");
		prop = (Map) Outside.resource(this,"props");
		
		if(!prop.containsKey(KEY_SYSTEMLIBRARYPATH)) return;
		
		String value = (String) prop.get(KEY_SYSTEMLIBRARYPATH);
		String[] n = value.split(";");
		
		for(int i=0;i<n.length;i++)
		handleNewLibPathID(n[i]);
	}
	
	
	private void handleNewLibPathID(String pathID)
	{
		try
		{
			File path = (File) fp.r(pathID);
			if(path==null) throw new Exception("Undefined path ID "+pathID);
			
			if(!path.exists()) path.mkdirs();
			modifyPath.v("add",path);
		}
		catch(Exception e)
		{Outside.err(this,"handleNewLibPathID(String)",e);}
	}
}
