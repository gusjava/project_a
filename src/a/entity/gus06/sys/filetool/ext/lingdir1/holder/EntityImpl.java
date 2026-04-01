package a.entity.gus06.sys.filetool.ext.lingdir1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20180201";}
	

	private Service findRoot;
	private Service guiHolder;

	private Map map;
	private File root;


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		guiHolder = Outside.service(this,"*gus06.dir.explorer.resource.ling");
	}
	
	
	public Object i() throws Exception
	{return guiHolder.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		guiHolder.p(root);
	}
}
