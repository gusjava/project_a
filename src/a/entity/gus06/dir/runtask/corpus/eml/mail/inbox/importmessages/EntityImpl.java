package a.entity.gus06.dir.runtask.corpus.eml.mail.inbox.importmessages;

import a.framework.*;
import java.io.File;
import javax.mail.Folder;
import javax.mail.Message;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160608";}


	private Service buildInbox;
	private Service askInfos;
	private Service messageToTimeStamp;
	private Service writeEml;
	
	public EntityImpl() throws Exception
	{
		buildInbox = Outside.service(this,"gus06.mail.store.pop3.folder.inbox");
		askInfos = Outside.service(this,"gus06.security.askinfo.loginpassword1");
		messageToTimeStamp = Outside.service(this,"gus06.mail.retrieve.message.date.sent.timestamp");
		writeEml = Outside.service(this,"gus06.dir.access.write.eml.autorename");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String[] infos = (String[]) askInfos.g();
		if(infos==null) return;
		
		Folder folder = (Folder) buildInbox.t(infos);
		folder.open(Folder.READ_ONLY);
		
		Message[] messages = folder.getMessages();
		
		int size = messages.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(int i=0;i<size;i++)
		{
			String timeStamp = (String) messageToTimeStamp.t(messages[i]);
			writeEml.p(new Object[]{dir,timeStamp,messages[i]});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		folder.close(false);
	}
}