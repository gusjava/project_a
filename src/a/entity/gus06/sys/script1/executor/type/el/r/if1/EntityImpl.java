package a.entity.gus06.sys.script1.executor.type.el.r.if1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150901";}

	
	private Service executePart1;
	private Service executePart2;
	private Service wrapping1;

	public EntityImpl() throws Exception
	{
		executePart1 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part1");
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
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
			wrapping1.p(new Object[]{context,tag,new Wrap()});
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
			
			Boolean if1 = (Boolean) main;
			if(if1==null) throw new Exception("Null value found for if expression");
			
			if(if1.booleanValue())
				executePart1.p(new Map[]{tag,context});
			else executePart2.p(new Map[]{tag,context});
		}
	}
}
