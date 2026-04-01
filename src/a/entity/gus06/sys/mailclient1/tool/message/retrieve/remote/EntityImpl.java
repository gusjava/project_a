package a.entity.gus06.sys.mailclient1.tool.message.retrieve.remote;

import a.framework.*;
import javax.mail.Message;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240320";}

	public static final String KEY_SENT = "sent";
	public static final String KEY_UID = "uid";

	private Service retrieveLocal;
	private Service buildData;
	private Service writeEml;
	private Service writeProp;
	private Service extractFiles;
	private Service emptyDir;
	
	public EntityImpl() throws Exception
	{
		retrieveLocal = Outside.service(this,"gus06.sys.mailclient1.tool.message.retrieve.local");
		buildData = Outside.service(this,"gus06.mail.retrieve.message.datamap");
		writeEml = Outside.service(this,"gus06.file.write.mail");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		extractFiles = Outside.service(this,"gus06.mail.retrieve.message.extractfiles");
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Message message = (Message) o[0];
		File storeDir = (File) o[1];
		
		Map data = (Map) buildData.t(message);
		
		String uid = (String) data.get(KEY_UID);
		String sent = (String) data.get(KEY_SENT);
		
		String id = sent+"_"+uid;
		File dir = new File(storeDir, id);
		
		if(!dir.exists()) dir.mkdirs();
		
		File emlFile = new File(dir, "message.eml");
		File propFile = new File(dir, "message.properties");
		File attachDir = new File(dir, "attachments");
		File doneFile = new File(dir, "done");
		
		if(!doneFile.exists())
		{
			emptyDir.p(dir);
			
			writeEml.p(new Object[]{emlFile, message});
			writeProp.p(new Object[]{propFile, data});
			extractFiles.p(new Object[]{message, attachDir});
			doneFile.createNewFile();
		}
		
		V holder = (V) retrieveLocal.t(dir);
		holder.v("message", message);
		
		return holder;
	}
}