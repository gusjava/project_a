package a.entity.gus06.swing.textcomp.cust.action.ctrl_b.execute.perform2;

import a.framework.*;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150527";}


	private Service openFile;
	private Service openURL;
	private Service openURL2;
	private Service browse;
	private Service replaceTag;


	public EntityImpl() throws Exception
	{
		openFile = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_b.execute.perform2.openfile");
		openURL = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_b.execute.perform2.openurl");
		openURL2 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_b.execute.perform2.openurl2");
		browse = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_b.execute.perform2.browse");
		replaceTag = Outside.service(this,"gus06.string.transform.replace.tag.now");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextArea comp = (JTextArea) o[0];
		String text = (String) o[1];
		
		String[] lines = text.split("\n");
		for(String line:lines) perform(comp,line);
	}
	
	
	
	private void perform(JTextArea comp, String line) throws Exception
	{
		line = line.trim();
		if(line.equals("") || line.equals(".")) return;
		line = (String) replaceTag.t(line);
		
		if(browse(comp,line)) return;
		if(cmd(line)) return;
		if(url(line)) return;
		
		if(beep(line)) return;
		if(openFile(line)) return;
		if(openURL(line)) return;
		if(keyword(line)) return;
	}
	
	
	
	private boolean browse(JTextArea comp, String line) throws Exception
	{
		if(!line.startsWith("->")) return false;
		return browse.f(new Object[]{comp,line.substring(2)});
	}
	
	private boolean cmd(String line) throws Exception
	{
		if(!line.startsWith("<>")) return false;
		Runtime.getRuntime().exec(line.substring(2));
		return true;
	}
	
	private boolean url(String line) throws Exception
	{
		if(!line.startsWith("u>")) return false;
		return openURL2(line.substring(2));
	}
	
	private boolean beep(String line)
	{
		if(!line.equals("beep")) return false;
		Toolkit.getDefaultToolkit().beep();
		return true;
	}
	
	
	
	private boolean openFile(String line) throws Exception
	{return openFile.f(line);}
	
	private boolean openURL(String line) throws Exception
	{return openURL.f(line);}
	
	private boolean openURL2(String line) throws Exception
	{return openURL2.f(line);}
	
	
	private boolean keyword(String line) throws Exception
	{
		if(line.equals("cmd"))
		{
			Runtime.getRuntime().exec("cmd.exe /C start");
			return true;
		}
		if(line.equals("explorer"))
		{
			Runtime.getRuntime().exec("explorer");
			return true;
		}
		if(line.equals("notepad"))
		{
			Runtime.getRuntime().exec("notepad");
			return true;
		}
		return false;
	}
}