package a.entity.gus06.sys.script1.executor.type.elmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20150830";}

	
	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("alias",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.alias"));
		put("comment",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.comment"));
		put("execute",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.execute"));
		put("output",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.output"));
		put("print",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.print"));
		put("println",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.println"));
		put("set",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.set"));
		put("set0",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.set0"));
		put("set1",		Outside.service(this,"gus06.sys.script1.executor.type.el.op.set1"));
		
		put("block",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.block"));
		put("block0",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.block0"));
		put("code",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.code"));
		put("each",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.each"));
		put("extends",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.extends1"));
		put("extends1",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.extends11"));
		put("for",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.for1"));
		put("if",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.if1"));
		put("ignore",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.ignore"));
		put("redirect",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.redirect"));
		put("repeat",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.repeat"));
		put("switch",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.switch1"));
		put("try",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.try1"));
		put("until",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.until"));
		put("while",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.while1"));
		put("clock",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.clock"));
		put("timeout",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.timeout"));
		put("run",		Outside.service(this,"gus06.sys.script1.executor.type.el.r.run"));
		
		put("call",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.call"));
		put("case",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.case1"));
		put("debug",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.debug"));
		put("else",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.else1"));
		put("elseif",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.elseif"));
		put("end",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.end"));
		put("include",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.include"));
		put("include1",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.include1"));
		put("parent",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.parent"));
		put("return",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.return1"));
		put("stop",		Outside.service(this,"gus06.sys.script1.executor.type.el.z.stop"));
		
		put("p",		get("print"));
		put("pp",		get("println"));
		put("e",		get("execute"));
		put("c",		get("comment"));
		put("s",		get("set"));
		put("s0",		get("set0"));
		put("s1",		get("set1"));
	}
	
	
	private void put(String name, Service s)
	{map.put(name,s);}
	
	private Service get(String name)
	{return (Service) map.get(name);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		if(!map.containsKey(name)) return null;
		return get(name);
	}
	
	
	public Object g() throws Exception
	{return map;}
}