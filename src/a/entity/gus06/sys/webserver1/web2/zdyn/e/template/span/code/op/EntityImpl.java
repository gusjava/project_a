package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.code.op;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}


	private Service op_entity;
	private Service op_string;
	private Service op_array;
	private Service op_list;
	private Service op_set;
	private Service op_map;
	private Service op_span;
	private Service op_main;
	private Service op_arg;
	private Service op_var;
	private Service op_g;
	private Service op_g1;
	private Service op_t;
	private Service op_r;
	private Service op_readProp;
	private Service op_readString;




	public EntityImpl() throws Exception
	{
		op_entity = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.entity");
		op_string = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.string");
		op_array = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.array");
		op_list = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.list");
		op_set = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.set");
		op_map = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.map");
		op_span = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.span");
		op_main = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.main");
		op_arg = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.arg");
		op_var = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.var");
		op_g = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.g");
		op_g1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.g1");
		op_t = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.t");
		op_r = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.r");
		op_readProp = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.readprop");
		op_readString = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.readstring");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		
		if(id.equals("entity")) return op_entity;
		if(id.equals("string")) return op_string;
		if(id.equals("array")) return op_array;
		if(id.equals("list")) return op_list;
		if(id.equals("set")) return op_set;
		if(id.equals("map")) return op_map;
		if(id.equals("span")) return op_span;
		if(id.equals("main")) return op_main;
		if(id.equals("arg")) return op_arg;
		if(id.equals("var")) return op_var;
		if(id.equals("g")) return op_g;
		if(id.equals("g1")) return op_g1;
		if(id.equals("t")) return op_t;
		if(id.equals("r")) return op_r;
		if(id.equals("readprop")) return op_readProp;
		if(id.equals("readstring")) return op_readString;
		
		throw new Exception("Unknown build operator id: "+id);
	}
}
