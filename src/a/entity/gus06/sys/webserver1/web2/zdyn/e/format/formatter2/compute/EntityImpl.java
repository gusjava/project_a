package a.entity.gus06.sys.webserver1.web2.zdyn.e.format.formatter2.compute;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}


	private Service findOp0;
	private Service findOp1;
	private Service findOp1a;
	private Service findOp2;


	public EntityImpl() throws Exception
	{
		findOp0 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop0");
		findOp1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop1");
		findOp1a = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop1a");
		findOp2 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String opName = (String) o[1];
		String info = (String) o[2];
		Map args = (Map) o[3];
		Map vars = (Map) o[4];
		
		
		T op2 = (T) findOp2.t(opName);
		if(op2!=null) return op2.t(new Object[]{mr,info,args,vars});
		
		T op1 = (T) findOp1.t(opName);
		if(op1!=null) return op1.t(new Object[]{mr,info});
		
		T op1a = (T) findOp1a.t(new Object[]{mr,opName});
		if(op1a!=null) return op1a.t(info);
		
		T op0 = (T) findOp0.t(opName);
		if(op0!=null) return op0.t(info);
		
		throw new Exception("Unknown operator: "+opName);
	}
}
