package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.code.build;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}

	public static final String DEFAULT_OP = "entity";
	
	
	private Service findOp0;
	private Service findOp;

	public EntityImpl() throws Exception
	{
		findOp0 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop0");
		findOp = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map span = (Map) o[0];
		String rule = (String) o[1];
		
		if(rule.equals("null")) return null;
		
		String[] n = rule.split("#",2);
		String op = n.length==2?n[0]:DEFAULT_OP;
		String info = n[n.length-1];
		
		
		T op0_ = (T) findOp0.t(op);
		if(op0_!=null) return op0_.t(info);
		
		T op_ = (T) findOp.t(op);
		return op_.t(new Object[]{span,info});
	}
}
