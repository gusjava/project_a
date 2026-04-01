package a.entity.gus06.sys.webserver1.web2.zdyn.e.format.findop1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}


	private Service op_prop;
	private Service op_prop_has;
	private Service op_path;
	private Service op_path_has;
	private Service op_ling;
	private Service op_main_value;
	private Service op_main_has;
	private Service op_dir;
	private Service op_param_get;
	private Service op_param_get_has;
	private Service op_param_post;
	private Service op_param_post_has;
	private Service op_g1;


	public EntityImpl() throws Exception
	{
		op_prop = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.prop");
		op_prop_has = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.prop.has");
		op_path = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.path");
		op_path_has = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.path.has");
		op_ling = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.ling");
		op_main_value = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.main.value");
		op_main_has = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.main.has");
		op_dir = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.dir");
		op_param_get = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.param.get");
		op_param_get_has = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.param.get.has");
		op_param_post = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.param.post");
		op_param_post_has = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.param.post.has");
		op_g1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.operator1.g1");
	}
	
	
	public Object t(Object obj) throws Exception
	{return findOp((String) obj);}
	
	
	private T findOp(String op) throws Exception
	{
		if(op.equals("prop")) return op_prop;
		if(op.equals("hasprop")) return op_prop_has;
		if(op.equals("path")) return op_path;
		if(op.equals("haspath")) return op_path_has;
		if(op.equals("ling")) return op_ling;
		if(op.equals("mainvalue")) return op_main_value;
		if(op.equals("hasmain")) return op_main_has;
		if(op.equals("dir")) return op_dir;
		if(op.equals("p_get")) return op_param_get;
		if(op.equals("has_p_get")) return op_param_get_has;
		if(op.equals("p_post")) return op_param_post;
		if(op.equals("has_p_post")) return op_param_post_has;
		if(op.equals("g1")) return op_g1;
		
		return null;
	}
}
