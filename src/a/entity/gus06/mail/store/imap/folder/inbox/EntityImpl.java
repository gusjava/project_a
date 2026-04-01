package a.entity.gus06.mail.store.imap.folder.inbox;

import a.framework.*;
import javax.mail.Store;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240313";}
	
	public static final String INBOX = "INBOX";


	private Service storeBuilderImap;


	public EntityImpl() throws Exception
	{
		storeBuilderImap = Outside.service(this,"gus06.mail.store.builder.imap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Store store = (Store) storeBuilderImap.t(obj);
		return store.getFolder(INBOX);
	}
}