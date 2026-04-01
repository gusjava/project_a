package a.entity.gus06.sys.webserver1.web2.zdyn.e.format.formatter1.compute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141001";}


	private Service findOp0;
	private Service findOp1;
	private Service findOp1a;


	public EntityImpl() throws Exception
	{
		findOp0 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop0");
		findOp1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop1");
		findOp1a = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.findop1a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String opName = (String) o[1];
		String info = (String) o[2];
		
		T op1 = (T) findOp1.t(opName);
		if(op1!=null) return op1.t(new Object[]{mr,info});
		
		T op1a = (T) findOp1a.t(new Object[]{mr,opName});
		if(op1a!=null) return op1a.t(info);
		
		T op0 = (T) findOp0.t(opName);
		if(op0!=null) return op0.t(info);
		
		throw new Exception("Unknown operator: "+opName);
	}
}
