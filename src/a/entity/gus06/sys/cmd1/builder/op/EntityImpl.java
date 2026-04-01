package a.entity.gus06.sys.cmd1.builder.op;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}


	private Service op_e;
	private Service op_p;
	private Service op_pg;
	private Service op_pt;
	private Service op_ptg;
	private Service op_runnable;
	private Service op_thread;
	private Service op_v;


	public EntityImpl() throws Exception
	{
		op_e = Outside.service(this,"gus06.sys.cmd1.builder.op.e");
		op_p = Outside.service(this,"gus06.sys.cmd1.builder.op.p");
		op_pg = Outside.service(this,"gus06.sys.cmd1.builder.op.pg");
		op_pt = Outside.service(this,"gus06.sys.cmd1.builder.op.pt");
		op_ptg = Outside.service(this,"gus06.sys.cmd1.builder.op.ptg");
		op_runnable = Outside.service(this,"gus06.sys.cmd1.builder.op.runnable");
		op_thread = Outside.service(this,"gus06.sys.cmd1.builder.op.thread");
		op_v = Outside.service(this,"gus06.sys.cmd1.builder.op.v");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String op = (String) obj;
		
		if(op.equals("e")) return op_e;
		if(op.equals("p")) return op_p;
		if(op.equals("pg")) return op_pg;
		if(op.equals("pt")) return op_pt;
		if(op.equals("ptg")) return op_ptg;
		if(op.equals("runnable")) return op_runnable;
		if(op.equals("thread")) return op_thread;
		if(op.equals("v")) return op_v;
		
		throw new Exception("Unknown operator: "+op);
	}
}
