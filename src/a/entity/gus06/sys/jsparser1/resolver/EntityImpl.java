package a.entity.gus06.sys.jsparser1.resolver;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20221013";}


	private Service block;
	private Service instruction;
	private Service expression;
	private Service array;
	private Service struct;
	private Service function;
	private Service call;

	public EntityImpl() throws Exception
	{
		block = Outside.service(this,"gus06.sys.jsparser1.resolve.block");
		instruction = Outside.service(this,"gus06.sys.jsparser1.resolve.instruction");
		expression = Outside.service(this,"gus06.sys.jsparser1.resolve.expression");
		array = Outside.service(this,"gus06.sys.jsparser1.resolve.array");
		struct = Outside.service(this,"gus06.sys.jsparser1.resolve.struct");
		function = Outside.service(this,"gus06.sys.jsparser1.resolve.function");
		call = Outside.service(this,"gus06.sys.jsparser1.resolve.call");
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("block")) return block;
		if(key.equals("instruction")) return instruction;
		if(key.equals("expression")) return expression;
		if(key.equals("array")) return array;
		if(key.equals("struct")) return struct;
		if(key.equals("function")) return function;
		if(key.equals("call")) return call;
		
		if(key.equals("keys")) return new String[]{
			"block","instruction","expression", 
			"array","struct","function","call"};
			
		throw new Exception("Unknown key: "+key);
	}
}