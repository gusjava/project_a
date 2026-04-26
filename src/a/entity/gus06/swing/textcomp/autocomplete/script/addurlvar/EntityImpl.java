package a.entity.gus06.swing.textcomp.autocomplete.script.addurlvar;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.net.URL;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20200108";}


	private Service clipboard;
	private Service insert;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		insert = Outside.service(this,"gus06.swing.textcomp.insert");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String s = (String) clipboard.g();
		URL url = new URL(s);
		String path = url.toString();
		
		String line = "$url='"+path+"'._tourl\n";
		
		insert.p(new Object[]{comp,line});
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String s = (String) clipboard.g();
		URL url = new URL(s);
		String path = url.toString();
		String line = "$"+key+"='"+path+"'._tourl\n";
		
		insert.p(new Object[]{comp,line});
	}
}
