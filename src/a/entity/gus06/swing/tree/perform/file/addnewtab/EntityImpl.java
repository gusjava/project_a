package a.entity.gus06.swing.tree.perform.file.addnewtab;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;

public class EntityImpl implements Entity, V, R, P {

	public String creationDate() {return "20180213";}


	private P tabAdder;
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		File file = (File) tree.getLastSelectedPathComponent();
		
		if(file==null) return;
		
		if(tabAdder==null) throw new Exception("tabAdder not initialized yet");
		tabAdder.p(file);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("tabAdder")) {tabAdder = (P) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("tabAdder")) return tabAdder;
		if(key.equals("keys")) return new String[]{"tabAdder"};
		throw new Exception("Unknown key: "+key);
	}
}