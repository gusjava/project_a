package a.entity.gus06.sys.filetool.ext.base1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150330";}
	

	private Service findRoot;
	private Service builder;
	private Service guiFactory;
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		builder = Outside.service(this,"gus06.sys.base1.builder");
		guiFactory = Outside.service(this,"factory#gus06.sys.base1.gui.maingui1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File root = (File) findRoot.t(obj);
		Object base = builder.t(new Holder(root));
		
		Object gui = guiFactory.g();
		((P)gui).p(base);
		return ((I)gui).i();
	}
	
	
	
	private class Holder implements G
	{
		private File root;
		public Holder(File root)
		{this.root = root;}
		
		public Object g() throws Exception
		{return root;}
	}
}
