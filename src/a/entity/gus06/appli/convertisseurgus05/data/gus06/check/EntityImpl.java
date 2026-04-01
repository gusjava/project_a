package a.entity.gus06.appli.convertisseurgus05.data.gus06.check;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150525";}


	private Service check;
	private Service getRoot;
	
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.entitydev.nametodir.check");
		getRoot = Outside.service(this,"gus06.appli.convertisseurgus05.option.dirgus06");
	}


	
	public boolean f(Object obj) throws Exception
	{
		File root = (File) getRoot.g();
		if(root==null) return false;
		return check.f(new Object[]{root,obj});
	}
}
