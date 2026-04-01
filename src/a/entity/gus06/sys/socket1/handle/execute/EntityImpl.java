package a.entity.gus06.sys.socket1.handle.execute;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20221120";}


	private Service scriptHolder;
	private Service formatLine;
	
	public EntityImpl() throws Exception
	{
		scriptHolder = Outside.service(this,"*gus06.sys.script1.main.main2");
		formatLine = Outside.service(this,"gus06.appli.laboscript.gui.consolegui.format");
	}
	
	
	public void p(Object obj) throws Exception
	{
		scriptHolder.p(formatLine.t(obj));
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("out")) {initOut((PrintStream) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void initOut(PrintStream out) throws Exception
	{
		scriptHolder.v("output",out);
	}
}