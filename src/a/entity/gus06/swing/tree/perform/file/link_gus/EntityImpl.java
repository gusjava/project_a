package a.entity.gus06.swing.tree.perform.file.link_gus;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250625";}


	private Service linkFiles;


	public EntityImpl() throws Exception
	{
		linkFiles = Outside.service(this,"gus06.dir.perform.linkgusfiles.fromclipboard");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		File file = (File) tree.getLastSelectedPathComponent();
		if(file==null) return;
		
		File dir = file.isFile()?file.getParentFile():file;
		boolean done = linkFiles.f(dir);
		
		if(!done) return;
				
		TreePath path = tree.getSelectionPath();
		if(file.isFile()) path = path.getParentPath();
		tree.getModel().valueForPathChanged(path,null);
	}
}