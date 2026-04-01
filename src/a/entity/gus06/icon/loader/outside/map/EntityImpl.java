package a.entity.gus06.icon.loader.outside.map;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191030";}


	private Service getRoot;
	private Service loader;

	public EntityImpl() throws Exception
	{
		getRoot = Outside.service(this,"gus06.icon.loader.outside");
		loader = Outside.service(this,"gus06.icon.loader.dir.map");
	}
	
	public Object g() throws Exception
	{
		File root = (File) getRoot.g();
		return loader.t(root);
	}
}