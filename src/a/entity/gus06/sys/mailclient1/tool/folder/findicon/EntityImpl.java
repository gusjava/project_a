package a.entity.gus06.sys.mailclient1.tool.folder.findicon;

import a.framework.*;
import javax.mail.Folder;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201114";}


	private Icon icon;
	private Icon iconInbox;
	private Icon iconOutbox;
	private Icon iconSent;
	private Icon iconJunk;
	private Icon iconDeleted;
	private Icon iconDraft;
	private Icon iconArchive;

	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#MAIL_folder");
		iconInbox = (Icon) Outside.resource(this,"icon#MAIL_folder_inbox");
		iconOutbox = (Icon) Outside.resource(this,"icon#MAIL_folder_outbox");
		iconSent = (Icon) Outside.resource(this,"icon#MAIL_folder_sent");
		iconJunk = (Icon) Outside.resource(this,"icon#MAIL_folder_trash");
		iconDeleted = (Icon) Outside.resource(this,"icon#MAIL_folder_empty");
		iconDraft = (Icon) Outside.resource(this,"icon#MAIL_folder_comment");
		iconArchive = (Icon) Outside.resource(this,"icon#MAIL_folder_zip");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Folder folder = (Folder) obj;
		String name = folder.getName().toLowerCase();
		
		if(name.equals("")) return icon;
		if(name.equals("inbox")) return iconInbox;
		if(name.equals("outbox")) return iconOutbox;
		if(name.equals("sent")) return iconSent;
		if(name.equals("junk")) return iconJunk;
		if(name.equals("deleted")) return iconDeleted;
		if(name.equals("drafts")) return iconDraft;
		if(name.equals("archive")) return iconArchive;
		
		return null;
	}
}