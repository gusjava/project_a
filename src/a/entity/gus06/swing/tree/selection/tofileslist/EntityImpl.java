package a.entity.gus06.swing.tree.selection.tofileslist;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151018";}

	
	public Object t(Object obj) throws Exception
	{return treeToSelection((JTree) obj);}
	
	
	private List treeToSelection(JTree tree)
	{
		TreePath[] paths = tree.getSelectionPaths();
		List files = new ArrayList();
		for(TreePath path:paths) files.add((File) path.getLastPathComponent());
		return files;
	}
}
