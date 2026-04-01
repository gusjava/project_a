package a.entity.gus06.swing.tree.perform.file.copyname;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200410";}


	private Service copyFileNames;
	private Service selectionToList;


	public EntityImpl() throws Exception
	{
		copyFileNames = Outside.service(this,"gus06.sys.clipboard1.p.listfiles.names");
		selectionToList = Outside.service(this,"gus06.swing.tree.selection.tofileslist");
	}
	
	
	public void p(Object obj) throws Exception
	{
		List files = treeToSelection((JTree) obj);
		if(!files.isEmpty()) copyFileNames.p(files);
	}
	
	private List treeToSelection(JTree tree) throws Exception
	{return (List) selectionToList.t(tree);}
}
