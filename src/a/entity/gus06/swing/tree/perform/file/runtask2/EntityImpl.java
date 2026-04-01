package a.entity.gus06.swing.tree.perform.file.runtask2;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251212";}

	private Service runtaskPath;

	public EntityImpl() throws Exception
	{
		runtaskPath = Outside.service(this,"gus06.sys.runtask2.input.path");
	}
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		File path = (File) tree.getLastSelectedPathComponent();
		runtaskPath.p(path);
	}
}
