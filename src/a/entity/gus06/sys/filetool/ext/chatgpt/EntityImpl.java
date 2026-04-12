package a.entity.gus06.sys.filetool.ext.chatgpt;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250112";}

	private Service findRoot;
	private Service newGui;

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		newGui = Outside.service(this,"factory#gus06.sys.filetool.ext.chatgpt.holder");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object gui = newGui.g();
		((P) gui).p(obj);
		return ((I) gui).i();
	}
}