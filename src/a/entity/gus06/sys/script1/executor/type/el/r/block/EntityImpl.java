package a.entity.gus06.sys.script1.executor.type.el.r.block;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151103";}
	
	public static final String VARNAME = "block";

	
	private Service wrapping1;
	private Service getBlock;
	private Service derivation;
	private Service getMain;
	private Service executeAll;

	public EntityImpl() throws Exception
	{
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
		getBlock = Outside.service(this,"gus06.sys.script1.access.context.block1.latest1");
		derivation = Outside.service(this,"gus06.sys.script1.executor.type.el.r.block.derivation");
		getMain = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler1.a.main");
		executeAll = Outside.service(this,"gus06.sys.script1.tool.execute.content.all");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{return new Executor((Map) obj);}
	
	
	
	private class Executor implements P
	{
		private Map tag;
		public Executor(Map tag) {this.tag = tag;}
		
		public void p(Object obj) throws Exception
		{
			Map context = (Map) obj;
			
			String name = (String) getMain.t(new Object[]{context,tag});
			Map tag1 = (Map) derivation.t(new Object[]{context,tag,name});
			
			Map blockMap = (Map) getBlock.t(context);
			blockMap.put(name,tag1);
			
			wrapping1.p(new Object[]{context,tag1,new Wrap()});
		}
	}
	
	
	private class Wrap implements P
	{
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			
			Map context = (Map) o[0];
			Map tag = (Map) o[1];
			Map pool1 = (Map) o[2];
			Object main = o[3];
			Map data = (Map) o[4];
			
			String name = (String) main;
			
			Map bMap = new HashMap();
			bMap.put("name",name);
			pool1.put(VARNAME,bMap);
			
			executeAll.p(new Map[]{tag,context});
		}
	}
}
