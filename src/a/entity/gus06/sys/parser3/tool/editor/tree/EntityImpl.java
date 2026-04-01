package a.entity.gus06.sys.parser3.tool.editor.tree;

import a.framework.*;
import javax.swing.tree.TreeModel;
import javax.swing.JTree;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221012";}


	private Service renderer;
	private Service buildModel;

	private JTree tree;
	
	public EntityImpl() throws Exception
	{
		buildModel = Outside.service(this,"gus06.sys.parser3.tool.editor.tree.buildmodel");
		renderer = Outside.service(this,"gus06.sys.parser3.tool.editor.tree.renderer");
		
		tree = new JTree();
		renderer.p(tree);
	}
	
	public Object i() throws Exception
	{return tree;}
	
	
	public void p(Object obj) throws Exception
	{
		TreeModel model = (TreeModel) buildModel.t(obj);
		tree.setModel(model);
	}
}