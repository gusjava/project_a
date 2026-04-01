package a.entity.gus06.sys.expression1.apply.op._mail_folder_inbox;

import a.framework.*;
import javax.mail.Store;
import javax.mail.Folder;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240327";}
	
	public static final String INBOX = "INBOX";

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Store) return toFolder((Store) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Folder toFolder(Store store) throws Exception
	{
		Folder folder = store.getFolder(INBOX);
		folder.open(Folder.READ_ONLY);
		return folder;
	}
}