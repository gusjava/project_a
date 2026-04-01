package a.entity.gus06.swing.textcomp.cust.autoedit.closexml.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170130";}
	
	public static final Pattern P = Pattern.compile("<([^>]+)>",Pattern.DOTALL);


	private Service rmScripts;


	public EntityImpl() throws Exception
	{
		rmScripts = Outside.service(this,"gus06.string.transform.format.html.rm.scripts");
	}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;

		int pos = comp.getCaretPosition();
		if(pos==0) throw new Exception("Invalid caret position after key pressed: 0");
		
		String text = comp.getText();
		
		String text0 = text.substring(0,pos);
		String text1 = text.substring(pos);
		
		int length0 = text0.length();
		if(length0<2) return;
		
		char lastChar = text0.charAt(length0-1);
		if(lastChar!='/') return;
		
		char lastChar2 = text0.charAt(length0-2);
		if(lastChar2!='<') return;
		
		String xmlName = findXmlName(text0);
		if(xmlName==null) return;
		
		String inserted = xmlName+">";
		int pos_ = pos+inserted.length();
		
		insert(comp,pos,inserted);
		comp.setCaretPosition(pos_);
	}
	
	
	
	private void insert(JTextComponent comp, int pos, String s) throws Exception
	{comp.getDocument().insertString(pos,s,null);}
	
	
	
	private String findXmlName(String text0) throws Exception
	{
		text0 = (String) rmScripts.t(text0);
		
		List l = new ArrayList();
		Matcher m = P.matcher(text0);
		
		while(m.find())
		{
			String value = m.group(1).trim();
			
			if(value.startsWith("!")) continue;
			if(value.endsWith("/")) continue;
			
			if(value.startsWith("/"))
			{
				String name = value.substring(1).trim();
				String last = last(l);
				if(last==null || !last.equals(name)) return null;
				
				l.remove(l.size()-1);
			}
			else
			{
				String name = value.split("[ \t\n]+",2)[0];
				l.add(name);
			}
		}
		return last(l);
	}
	
	
	private String last(List l)
	{
		if(l.isEmpty()) return null;
		return (String) l.get(l.size()-1);
	}
}
