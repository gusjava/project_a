package a.entity.gus06.swing.textcomp.cust.action.ctrl_b.execute.perform2.browse;

import a.framework.*;
import javax.swing.JTextArea;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150527";}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextArea comp = (JTextArea) o[0];
		String line = (String) o[1];
		
		
		String[] nn = line.split("\t");
		String s = comp.getText();
		int offset = s.indexOf("[START]");
		if(offset==-1) return false;
		
		for(String n:nn)
		offset = browse(s,n,offset);
		
		if(offset==-1) return false;
		
		comp.setCaretPosition(offset);
		return true;
	}
	
	
	
	
	private int browse(String s, String part, int offset) throws Exception
	{
		if(offset==-1) return -1;
		if(part.startsWith(":")) return browse_a(s,part.substring(1),offset);
		if(part.startsWith("!")) return browse_b(s,part.substring(1),offset);
		throw new Exception("Invalid browse rule: "+part);
	}
	
	
	private int browse_a(String s, String part, int offset) throws Exception
	{
		return s.indexOf(part,offset);
	}
	
	
	private int browse_b(String s, String part, int offset) throws Exception
	{
		Pattern p = Pattern.compile(part,Pattern.DOTALL);
		Matcher m = p.matcher(s);
		if(!m.find(offset)) return -1;
		return m.start();
	}
}
