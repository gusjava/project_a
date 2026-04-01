package a.entity.gus06.sys.mailclient1.gui.tab1.messages.engine;

import a.framework.*;
import javax.mail.Message;
import javax.mail.Folder;
import java.io.File;

public class EntityImpl extends S1 implements Entity, R, V, G, Runnable {

	public String creationDate() {return "20240324";}
	
	public static final String MODE_ALL = "all";
	public static final String MODE_REMOTE = "remote";
	public static final String MODE_LOCAL = "local";


	private Service retrieveLocal;
	private Service retrieveRemote;
	private Service deleteDir;
	
	private File root;
	private File folderDir;
	private Folder folder;
	private String mode = MODE_ALL;
	
	private Object holder;
	
	private int index = -1;
	private int totalNumber = -1;
	private int storedNumber = -1;
	private int pendingNumber = -1;
	
	private boolean interrupt = false;
	
	
	public EntityImpl() throws Exception
	{
		retrieveLocal = Outside.service(this,"gus06.sys.mailclient1.tool.message.retrieve.local");
		retrieveRemote = Outside.service(this,"gus06.sys.mailclient1.tool.message.retrieve.remote");
		deleteDir = Outside.service(this,"gus06.dir.perform.remove");
	}
	
	
	public Object g() throws Exception
	{return holder;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("progress")) return progress();
		if(key.equals("keys")) return new String[]{"progress"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("folder")) {folder = (Folder) obj;return;}
		if(key.equals("root")) {root = (File) obj;return;}
		if(key.equals("mode")) {mode = (String) obj;return;}
		if(key.equals("interrupt")) {interrupt = true;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private String progress()
	{
		if(pendingNumber==-1) return "";
		if(index==-1) return "";
		return index+" / "+totalNumber;
	}
	
	
	public void run()
	{
		try
		{
			perform();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private void perform() throws Exception
	{
		if(folder==null) throw new Exception("Folder not found");
		if(root==null) throw new Exception("Root not found");
		interrupt = false;
		
		folderDir = new File(root, folder.getName());
		folderDir.mkdirs();
		
		Message[] mm = folder.getMessages();
		File[] dd = folderDir.listFiles();
		
		totalNumber = mm.length;
		storedNumber = dd.length;
		pendingNumber = totalNumber - storedNumber;
		
		for(int i=0;i<totalNumber;i++)
		{
			index = i+1;
			
			if(i<storedNumber-1)
				handleLocal(dd[i]);
			else if(i==storedNumber-1)
			{
				if(new File(dd[i],"done").exists())
					handleLocal(dd[i]);
				else
				{
					deleteDir.p(dd[i]);
					handleRemote(mm[i]);
				}
			}
			else handleRemote(mm[i]);
			
			if(interrupt) return;
		}
	}
	
	
	
	private void handleLocal(File d) throws Exception
	{
		if(!localEnabled()) return;
		holder = retrieveLocal.t(d);
		newMessageLocalRetrieved();
	}
	
	private void handleRemote(Message m) throws Exception
	{
		if(!remoteEnabled()) return;
		holder = retrieveRemote.t(new Object[]{m, folderDir});
		newMessageRemoteRetrieved();
	}
	
	
	
	private boolean remoteEnabled()
	{return mode.equals(MODE_ALL) || mode.equals(MODE_REMOTE);}
	
	private boolean localEnabled()
	{return mode.equals(MODE_ALL) || mode.equals(MODE_LOCAL);}
	
	
	
	private void newMessageLocalRetrieved()
	{send(this,"newMessageLocalRetrieved()");}
	
	
	private void newMessageRemoteRetrieved()
	{send(this,"newMessageRemoteRetrieved()");}
}