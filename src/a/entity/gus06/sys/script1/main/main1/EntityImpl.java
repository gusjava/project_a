package a.entity.gus06.sys.script1.main.main1;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160602";}


	private Service engine;
	private Service contextBuilder;
	private Service printStreamToP;
	
	private PrintStream out;
	
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.script1.engine");
		contextBuilder = Outside.service(this,"gus06.sys.script1.context.builder1");
		printStreamToP = Outside.service(this,"gus06.convert.printstreamtop");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
			Object src = o[0];
			Object output = o[1];
			
			Map context = (Map) contextBuilder.t(output);
			engine.p(new Object[]{src,context});
			return;
		}
		
		P output = (P) printStreamToP.t(out);
		Map context = (Map) contextBuilder.t(output);
		engine.p(new Object[]{obj,context});
	}
}
