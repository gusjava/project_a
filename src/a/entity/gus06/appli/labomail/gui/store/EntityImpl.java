package a.entity.gus06.appli.labomail.gui.store;

import a.framework.*;
import javax.mail.Store;
import javax.mail.Folder;
import javax.mail.Message;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160607";}
	
	private SimpleDateFormat yyyyMMdd_HHmmss = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String yyyyMMdd_HHmmss(Date d)
	{
		if(d==null) return null;
		return yyyyMMdd_HHmmss.format(d);
	}
	
	
	
	private Store store;
	private boolean isPop3;
	
	private JTextArea area;
	private JScrollPane scroll;


	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setEditable(false);
		
		scroll = new JScrollPane(area);
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	
	public void p(Object obj) throws Exception
	{
		store = (Store) obj;
		if(store==null) return;
		
		area.setText("");
		println(store.getClass().getName());
		
		isPop3 = store instanceof com.sun.mail.pop3.POP3Store;
		
		if(isPop3)
		{
			printlnINBOX();
		}
		else
		{
			println("-------------------");
			println("DEFAULT");
			printFolder1(store.getDefaultFolder(),"");
			
			println("-------------------");
			println("PERSONAL NAMESPACES");
			Folder[] ff = store.getPersonalNamespaces();
			if(ff!=null) for(Folder f:ff) printFolder1(f,"");
			
			println("-------------------");
			println("SHARED NAMESPACES");
			Folder[] ss = store.getSharedNamespaces();
			if(ss!=null) for(Folder s:ss) printFolder1(s,"");
		}
		
	}
	
	
	
	
	private void printlnINBOX()
	{
		try
		{
			printFolder2(store.getFolder("INBOX"),"");
		}
		catch(Exception e)
		{
			Outside.err(this,"printlnINBOX()",e);
			println("Error: "+e);
			println("");
		}
	}
	
	
	
	
	private void printFolder1(Folder f, String off)
	{
		try
		{
			println(off+"folder");
			println(off+"-name="+f.getName());
			println(off+"-fullname="+f.getFullName());
			println(off+"-exists="+f.exists());
			
			if(f.exists())
			{
				println(off+"-type="+f.getType()+" (HOLDS_FOLDERS="+Folder.HOLDS_FOLDERS+" HOLDS_MESSAGES="+Folder.HOLDS_MESSAGES+")");
				
				if((f.getType() & Folder.HOLDS_MESSAGES) != 0)
				{
					println(off+"-message count="+f.getMessageCount());
					println(off+"-unread message count="+f.getUnreadMessageCount());
				}
				if((f.getType() & Folder.HOLDS_FOLDERS) != 0)
				{
					println(off+"-subfolder count="+f.list().length);
					println("");
					
					Folder[] cc = f.list();
					for(Folder c:cc) printFolder1(c,off+"\t");
				}
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"printFolder1(Folder,String)",e);
			println("Error: "+e);
			println("");
		}
	}
	
	
	
	
	private void printFolder2(Folder f, String off)
	{
		try
		{
			f.open(Folder.READ_ONLY);
			printFolder1(f,off);
			
			Message[] mm = f.getMessages();
			for(int i=0;i<mm.length;i++)
			{
				String t1 = yyyyMMdd_HHmmss(mm[i].getReceivedDate());
				String t2 = yyyyMMdd_HHmmss(mm[i].getSentDate());
				String subject = mm[i].getSubject();
				
				println(i+": "+t1+"->"+t2+" : "+subject);
			}
			
			f.close(false);
		}
		catch(Exception e)
		{
			Outside.err(this,"printFolder2(Folder,String)",e);
			println("Error: "+e);
			println("");
		}
	}
	
	
	private void println(String m)
	{area.append(m+"\n");}
}