package a.entity.gus06.swing.tree.perform.file.showinframe;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180213";}


	private Service showInFrame;
	private Service selectionToList;

	public EntityImpl() throws Exception
	{
		showInFrame = Outside.service(this,"gus06.file.editor.show.inframe2");
		selectionToList = Outside.service(this,"gus06.swing.tree.selection.tofileslist");
	}
	
	public void p(Object obj) throws Exception
	{
		List files = treeToSelection((JTree) obj);
		showInFrame.p(files);
	}
	
	private List treeToSelection(JTree tree) throws Exception
	{return (List) selectionToList.t(tree);}
}