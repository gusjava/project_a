package a.entity.gus06.find.treemodel;

import a.framework.*;
import javax.swing.tree.TreeModel;
import javax.swing.JTree;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161221";}


	private Service builder1;
	private Service buildFromStringArray;
	
	public EntityImpl() throws Exception
	{
		builder1 = Outside.service(this,"gus06.swing.tree.model.builder1");
		buildFromStringArray = Outside.service(this,"gus06.swing.tree.model.build.fromstringarray.tab");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof TreeModel) return obj;
		if(obj instanceof JTree) return ((JTree) obj).getModel();
		if(obj instanceof String[]) return buildFromStringArray.t(obj);
		
		if(obj instanceof Map) return builder1.t(obj);
		if(obj instanceof List) return builder1.t(obj);
		if(obj instanceof Object[]) return builder1.t(obj);
		
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
}
