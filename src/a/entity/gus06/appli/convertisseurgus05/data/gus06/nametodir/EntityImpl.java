package a.entity.gus06.appli.convertisseurgus05.data.gus06.nametodir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150525";}


	private Service nameToDir;
	private Service getRoot;
	
	
	public EntityImpl() throws Exception
	{
		nameToDir = Outside.service(this,"gus06.entitydev.nametodir");
		getRoot = Outside.service(this,"gus06.appli.convertisseurgus05.option.dirgus06");
	}


	
	public Object t(Object obj) throws Exception
	{
		File root = (File) getRoot.g();
		if(root==null) return null;
		return nameToDir.t(new Object[]{root,obj});
	}
}
