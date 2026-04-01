package a.entity.gus06.swing.tree.perform.file.changeroot;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;

public class EntityImpl implements Entity, V, R, P {

	public String creationDate() {return "20151003";}


	private P rootChanger;
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		File file = (File) tree.getLastSelectedPathComponent();
		
		if(file==null) return;
		
		File root = (File) tree.getModel().getRoot();
		File newRoot = file.equals(root)? root.getParentFile() : file;
		
		if(newRoot==null) return;
		
		if(rootChanger==null) throw new Exception("rootChanger not initialized yet");
		rootChanger.p(new File[]{root,newRoot});
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("rootChanger")) {rootChanger = (P) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("rootChanger")) return rootChanger;
		if(key.equals("keys")) return new String[]{"rootChanger"};
		throw new Exception("Unknown key: "+key);
	}
}