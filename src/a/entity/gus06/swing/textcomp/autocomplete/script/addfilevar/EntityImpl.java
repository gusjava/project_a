package a.entity.gus06.swing.textcomp.autocomplete.script.addfilevar;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.io.File;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20191201";}


	private Service clipboard;
	private Service insert;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.sys.clipboard1.g.file2");
		insert = Outside.service(this,"gus06.swing.textcomp.insert");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		File file = (File) clipboard.g();
		String path = file.getAbsolutePath().replace("\\","/");
		
		String varName = file.isDirectory() ? "dir" : "file";
		String line = "$"+varName+"='"+path+"'._tofile\n";
		
		insert.p(new Object[]{comp,line});
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		File file = (File) clipboard.g();
		String path = file.getAbsolutePath().replace("\\","/");
		String line = "$"+key+"='"+path+"'._tofile\n";
		
		insert.p(new Object[]{comp,line});
	}
}
