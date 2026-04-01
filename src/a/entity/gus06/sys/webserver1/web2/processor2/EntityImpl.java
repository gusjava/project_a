package a.entity.gus06.sys.webserver1.web2.processor2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}
	
	public static final String KEY_OUTPUT = "output";

	private Service prepare1;
	private Service prepare2;
	private Service output;
	private Service process;

	public EntityImpl() throws Exception
	{
		prepare1 = Outside.service(this,"gus06.sys.webserver1.web2.processor2.prepare1");
		prepare2 = Outside.service(this,"gus06.sys.webserver1.web2.processor2.prepare2");
		output = Outside.service(this,"gus06.sys.webserver1.web2.processor2.output");
		process = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.start");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object main1 = prepare1.t(obj);
		Object main2 = prepare2.t(main1);
		process.p(main2);
		return output.t(main1);
	}
}
