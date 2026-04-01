package a.entity.gus06.sys.expression1.apply.op._jtree;

import a.framework.*;
import javax.swing.tree.TreeModel;
import java.util.Map;
import java.util.List;
import javax.swing.JTree;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180225";}

	

	private Service findTreeModel;
	
	public EntityImpl() throws Exception
	{
		findTreeModel = Outside.service(this,"gus06.find.treemodel");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof TreeModel)	return perform(obj);
		if(obj instanceof Map)		return perform(obj);
		if(obj instanceof List)		return perform(obj);
		if(obj instanceof String[])	return perform(obj);
		if(obj instanceof Object[])	return perform(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private JTree perform(Object obj) throws Exception
	{
		TreeModel model = (TreeModel) findTreeModel.t(obj);
		return new JTree(model);
	}
}