package a.entity.gus06.swing.tree.perform.file.open;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}


	private Service executeFile;
	private Service selectionToList;


	public EntityImpl() throws Exception
	{
		executeFile = Outside.service(this,"gus06.file.execute.generic");
		selectionToList = Outside.service(this,"gus06.swing.tree.selection.tofileslist");
	}
	
	
	public void p(Object obj) throws Exception
	{
		List files = treeToSelection((JTree) obj);
		for(int i=0;i<files.size();i++)
		{
			File file = (File) files.get(i);
			executeFile.p(file);
		}
	}
	
	private List treeToSelection(JTree tree) throws Exception
	{return (List) selectionToList.t(tree);}
}
