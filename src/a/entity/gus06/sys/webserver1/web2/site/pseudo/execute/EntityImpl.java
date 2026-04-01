package a.entity.gus06.sys.webserver1.web2.site.pseudo.execute;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141016";}


	private Service findOp1;
	private Service findOp2;


	public EntityImpl() throws Exception
	{
		findOp1 = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.execute.findop1");
		findOp2 = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.execute.findop2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String pseudo = (String) o[1];
		String cmd = (String) o[2];
		
		String[] n = cmd.split(":",2);
		String op = n[0];
		String info = n[1];
		
		P p = (P) findOp(op,pseudo);
		if(p==null) throw new Exception("Operator not found: "+op);
		
		p.p(new Object[]{mr,info});
	}
	
	
	
	
	private P findOp(String op, String pseudo) throws Exception
	{
		if(pseudo.equals("gus"))
		{
			P r = (P) findOp2.r(op);
			if(r!=null) return r;
		}
		return (P) findOp1.r(op);
	}
}
