package a.entity.gus06.sys.filetool.ext.base2;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221104";}
	

	private Service findRoot;
	private Service builder;
	private Service guiFactory;
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		builder = Outside.service(this,"gus06.sys.base2.builder");
		guiFactory = Outside.service(this,"factory#gus06.sys.base2.gui.maingui1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File root = (File) findRoot.t(obj);
		Object base = builder.t(root);
		
		Object gui = guiFactory.g();
		((P)gui).p(base);
		return ((I)gui).i();
	}
}