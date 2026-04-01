package a.entity.gus06.sys.mailclient1.tool.folder.children;

import a.framework.*;
import javax.mail.Folder;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201114";}


	
	public Object t(Object obj) throws Exception
	{
		Folder folder = (Folder) obj;
		List list = new ArrayList();
		
		if(!folder.exists()) return list;
		if((folder.getType() & Folder.HOLDS_FOLDERS) == 0) return list;
		
		Folder[] children = folder.list();
		if(children.length==0) return list;
		
		add(list,findFolder(children,"inbox"));
		add(list,findFolder(children,"outbox"));
		add(list,findFolder(children,"sent"));
		add(list,findFolder(children,"junk"));
		add(list,findFolder(children,"deleted"));
		add(list,findFolder(children,"drafts"));
		add(list,findFolder(children,"archive"));
		add(list,findFolder(children,"notes"));
		
		for(Folder child : children) add(list,child);
		
		return list;
	}
	
	
	private void add(List list, Folder folder)
	{
		if(folder==null) return;
		if(!list.contains(folder)) list.add(folder);
	}
	
	
	private Folder findFolder(Folder[] children, String name)
	{
		for(Folder child : children)
		if(child.getName().toLowerCase().equals(name)) return child;
		return null;
	}
}