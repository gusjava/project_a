package a.entity.gus06.swing.tree.perform.file.cutcontent;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151021";}


	private Service toDir;
	private Service toFile;


	public EntityImpl() throws Exception
	{
		toDir = Outside.service(this,"gus06.sys.capturescreen1.write.todir");
		toFile = Outside.service(this,"gus06.sys.capturescreen1.write.tofile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		File file = (File) tree.getLastSelectedPathComponent();
		if(file==null) return;
		
		boolean done = perform(file);
		if(!done) return;
		
		TreePath path = tree.getSelectionPath();
		if(file.isFile()) path = path.getParentPath();
		tree.getModel().valueForPathChanged(path,null);
	}
	
	
	
	private boolean perform(File file) throws Exception
	{
		if(file.isFile()) return toFile.f(file);
		if(file.isDirectory()) return toDir.f(file);
		return toFile.f(file);
	}
}
