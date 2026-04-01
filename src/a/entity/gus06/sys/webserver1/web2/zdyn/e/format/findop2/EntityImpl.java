package a.entity.gus06.sys.webserver1.web2.zdyn.e.format.findop2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}


	private Service op_arg;
	private Service op_var;
	private Service op_size;
	private Service op_typeof;
	private Service op_tostring;
	private Service op_tohtml;
	private Service op_join;
	
	private Service op_hasvar;
	private Service op_hasarg;
	private Service op_t;
	private Service op_r;
	private Service op_g;
	private Service op_g1;
	private Service op_filename;
	private Service op_filepath;
	private Service op_filemd5;
	private Service op_readprop;
	private Service op_readstring;
	
	private Service op_isdir;
	private Service op_isfile;
	private Service op_isempty;
	private Service op_isnull;
	private Service op_isstring;
	private Service op_ismap;
	private Service op_islist;
	private Service op_isset;
	private Service op_isarray;


	public EntityImpl() throws Exception
	{
		op_arg = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.arg");
		op_var = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var");
		op_size = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.size");
		op_typeof = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.typeof");
		op_tostring = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.tostring");
		op_tohtml = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.tohtml");
		op_join = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.join");
		
		op_hasvar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.has");
		op_hasarg = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.arg.has");
		op_t = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.t");
		op_r = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.r");
		op_g = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.g");
		op_g1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.g1");
		op_filename = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.filename");
		op_filepath = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.filepath");
		op_filemd5 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.filemd5");
		op_readprop = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.readprop");
		op_readstring = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.readstring");
		
		op_isdir = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.isdir");
		op_isfile = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.file.isfile");
		op_isempty = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.isempty");
		op_isnull = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.isnull");
		op_isstring = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.isstring");
		op_ismap = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.ismap");
		op_islist = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.islist");
		op_isset = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.isset");
		op_isarray = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator2.var.isarray");
	}
	
	
	public Object t(Object obj) throws Exception
	{return findOp((String) obj);}
	
	
	private T findOp(String op) throws Exception
	{
		if(op.equals("arg")) return op_arg;
		if(op.equals("var")) return op_var;
		if(op.equals("size")) return op_size;
		if(op.equals("typeof")) return op_typeof;
		if(op.equals("tostring")) return op_tostring;
		if(op.equals("tohtml")) return op_tohtml;
		if(op.equals("join")) return op_join;
		
		if(op.equals("hasvar")) return op_hasvar;
		if(op.equals("hasarg")) return op_hasarg;
		if(op.equals("t")) return op_t;
		if(op.equals("r")) return op_r;
		if(op.equals("g")) return op_g;
		if(op.equals("g1")) return op_g1;
		if(op.equals("filename")) return op_filename;
		if(op.equals("filepath")) return op_filepath;
		if(op.equals("filemd5")) return op_filemd5;
		if(op.equals("readprop")) return op_readprop;
		if(op.equals("readstring")) return op_readstring;
		
		if(op.equals("isdir")) return op_isdir;
		if(op.equals("isfile")) return op_isfile;
		if(op.equals("isempty")) return op_isempty;
		if(op.equals("isnull")) return op_isnull;
		if(op.equals("isstring")) return op_isstring;
		if(op.equals("ismap")) return op_ismap;
		if(op.equals("islist")) return op_islist;
		if(op.equals("isset")) return op_isset;
		if(op.equals("isarray")) return op_isarray;
		
		return null;
	}
}
