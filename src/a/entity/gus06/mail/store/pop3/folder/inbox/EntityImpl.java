package a.entity.gus06.mail.store.pop3.folder.inbox;

import a.framework.*;
import javax.mail.Store;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160608";}
	
	public static final String INBOX = "INBOX";


	private Service storeBuilderPop3;


	public EntityImpl() throws Exception
	{
		storeBuilderPop3 = Outside.service(this,"gus06.mail.store.builder.pop3");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Store store = (Store) storeBuilderPop3.t(obj);
		return store.getFolder(INBOX);
	}
}