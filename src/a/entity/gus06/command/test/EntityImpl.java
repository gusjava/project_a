package a.entity.gus06.command.test;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}

	private Service builder;
	private Service print;
	private Map map;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.command.test.builder");
		print = Outside.service(this,"gus06.print.object");
		map = new HashMap();
	}
	
	
	public void p(Object obj) throws Exception
	{
		
String rule = (String) obj;
		if(rule.equals("reset")){map.clear();return;}

		String[] n = rule.split(" ",2);
		if(n.length!=2) throw new Exception("Wrong rule: "+rule);

		String op = n[0];
		String info = n[1];

		if(op.equals("a"))
		{
			String[] m = info.split("=",2);
			if(m.length!=2) throw new Exception("Wrong rule: "+rule);
			map.put(m[0],build(m[1]));
			return;
		}
		if(op.equals("p"))
		{
			print.p(build(info));
			return;
		}
		if(op.equals("e"))
		{
			execute(build(info));
			return;
		}
	}



	private Object build(String info) throws Exception
	{return builder.t(new Object[]{map,info});}


	private void execute(Object obj) throws Exception
	{
		if(obj instanceof E) ((E) obj).e();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
