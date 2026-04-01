package a.entity.gus06.swing.tree.perform.file.search;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191227";}


	private Service selectionToList;
	private Service goToTarget;
	private Service fromDir;
	private Service fromFile;

	public EntityImpl() throws Exception
	{
		selectionToList = Outside.service(this,"gus06.swing.tree.selection.tofileslist");
		goToTarget = Outside.service(this,"gus06.swing.tree.perform.file.gototarget");
		fromDir = Outside.service(this,"gus06.swing.tree.perform.file.search.fromdir");
		fromFile = Outside.service(this,"gus06.swing.tree.perform.file.search.fromfile");
	}
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		
		Map search = (Map) ((R)tree).r("search");
		if(!search.isEmpty())
		{
			search.clear();
			tree.repaint();
			return;
		}
		
		List files = treeToSelection(tree);
		if(files.isEmpty()) return;
		
		if(files.size()==1 && ((File)files.get(0)).isFile())
			fromFile.p(new Object[]{search, tree.getModel().getRoot(), files.get(0)});
		else fromDir.p(new Object[]{search, files});
		
		boolean selected = goToTarget.f(tree);
		if(!selected) tree.repaint();
	}
	
	
	private List treeToSelection(JTree tree) throws Exception
	{return (List) selectionToList.t(tree);}
}