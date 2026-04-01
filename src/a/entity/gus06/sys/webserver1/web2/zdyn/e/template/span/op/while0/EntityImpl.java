package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.while0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}
	
	public static final int LIMIT = 200;


	private Service formatInfo;
	private Service handleContent;
	private Service eval;
	
	public EntityImpl() throws Exception
	{
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo");
		handleContent = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.handlecontent");
		eval = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map span = (Map) obj;
		Map args = (Map ) span.get("args");
		
		int k = 0;
		while(isInfoTrue(span) && k<LIMIT)
		{handleContent(span,args);k++;}
	}
	
	
	private void handleContent(Map span, Map args) throws Exception
	{handleContent.p(new Object[]{span,args});}
	
	
	
	private boolean isInfoTrue(Map span) throws Exception
	{
		String info = (String) formatInfo.t(span);
		return eval.f(info);
	}
}
