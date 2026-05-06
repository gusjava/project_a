package a.entity.gus06.file.read.string.tree;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}


	private Service read;
	private Service buildTree;


	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.file.string.read.v1");
		buildTree = Outside.service(this,"gus06.data.transform.string.tree1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) read.t(obj);
		return buildTree.t(text);
	}
}
