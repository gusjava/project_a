package a.entity.gus06.swing.frame.show2.tree;

import a.framework.*;
import javax.swing.tree.TreeModel;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180331";}


	private Service show;
	private Service newViewer;
	private Service findTreeModel;


	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		newViewer = Outside.service(this,"factory#gus06.data.viewer.treemodel");
		findTreeModel = Outside.service(this,"gus06.find.treemodel");
	}
	
	
	private Object comp(Object obj) throws Exception
	{
		TreeModel model = (TreeModel) findTreeModel.t(obj);
		Object viewer = newViewer.g();
		((P)viewer).p(model);
		return ((I)viewer).i();
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = comp(o[0]);
		return show.t(new Object[]{comp,o[1],o[2]});
	}
}
