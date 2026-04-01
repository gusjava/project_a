package a.entity.gus06.sys.mailclient1.tool.folder.buildholder;

import a.framework.*;
import javax.swing.Icon;
import javax.mail.Folder;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201116";}

	public static final String DEFAULT_NAME = "Folders";


	private Service findIcon;
	private Service findChildren;
	
	private Icon iconDir;
	private Icon iconDir_;

	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.sys.mailclient1.tool.folder.findicon");
		findChildren = Outside.service(this,"gus06.sys.mailclient1.tool.folder.children");
		
		iconDir = (Icon) Outside.resource(this,"icon#dir2");
		iconDir_ = (Icon) Outside.resource(this,"icon#dir2_");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return new Holder((Folder) obj);
	}
	
	
	private class Holder implements R, E
	{
		private Folder folder;
		private Icon icon;
		private Icon icon_;
		
		private int count = -1;
		private int unread = -1;
		private String name;
		private String title;
		private String fullTitle;
		private List children;
		
		public Holder(Folder folder) throws Exception
		{
			this.folder = folder;
			init();
		}
		
		public void e() throws Exception
		{init();}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("folder")) return folder;
			if(key.equals("name")) return name;
			if(key.equals("icon")) return icon;
			if(key.equals("icon_")) return icon_;
			if(key.equals("count")) return count();
			if(key.equals("unread")) return unread();
			if(key.equals("title")) return title();
			if(key.equals("fullTitle")) return fullTitle();
			if(key.equals("children")) return children();
			
			if(key.equals("keys")) return new String[]{
				"folder","name",
				"icon","icon_",
				"count","unread",
				"title","fullTitle","children"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		private void init() throws Exception
		{
			icon = (Icon) findIcon.t(folder);
			if(icon==null)
			{
				icon = iconDir;
				icon_ = iconDir_;
			}
			
			name = folder.getName();
			if(name.equals("")) name = DEFAULT_NAME;
			
			count = -1;
			unread = -1;
			title = null;
			fullTitle = null;
		}
		
		
		private int count() throws Exception
		{
			if(count==-1) count = folder.getMessageCount();
			return count;
		}
		
		private int unread() throws Exception
		{
			if(folder.getName().equals("")) return -1;
			if(unread==-1) unread = folder.getUnreadMessageCount();
			return unread;
		}
		
		
		// TITLE
		
		private String title() throws Exception
		{
			if(title==null) initTitle();
			return title;
		}
		
		private void initTitle() throws Exception
		{
			try
			{
				int u = unread();
				if(u>0) title = name+" ("+u+")";
				else title = name;
			}
			catch(Exception e)
			{
				title = name+" #"+e.getMessage();
			}
		}
		
		
		// FULL TITLE
		
		private String fullTitle() throws Exception
		{
			if(fullTitle==null) initFullTitle();
			return fullTitle;
		}
		
		private void initFullTitle() throws Exception
		{
			StringBuffer b = new StringBuffer();
			b.append(name);
			b.append(" - ");
			int c = count();
			if(c==0) b.append("empty");
			else
			{
				String messageNb = c==1 ? "1 message" : c+" messages";
				b.append(messageNb);
				
				int u = unread();
				if(u>0) b.append(" ("+u+" unread)");
			}
			fullTitle = b.toString();
		}
		
		
		// CHILDREN
		
		private List children() throws Exception
		{
			if(children==null) initChildren();
			return children;
		}
		
		private void initChildren() throws Exception
		{
			List list = (List) findChildren.t(folder);
			children = new ArrayList();
			for(int i=0;i<list.size();i++)
			{
				Folder f = (Folder) list.get(i);
				children.add(new Holder(f));
			}
		}
	}
}