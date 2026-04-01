package a.entity.gus06.swing.textcomp.autocomplete.script.addstringvar;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20220613";}


	private Service clipboard;
	private Service insert;
	private Service formatJava;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.sys.clipboard1.g.string");
		insert = Outside.service(this,"gus06.swing.textcomp.insert");
		formatJava = Outside.service(this,"gus06.string.transform.format.java.string1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		v("data",obj);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;	
			
		String data = (String) clipboard.g();
		String dataF = formatJava(data);
		
		String line = "$var=\""+dataF+"\"\n";
		insert.p(new Object[]{comp,line});
	}
	
	
	
	private String formatJava(String s) throws Exception
	{return (String) formatJava.t(s);}
	
	
	
}