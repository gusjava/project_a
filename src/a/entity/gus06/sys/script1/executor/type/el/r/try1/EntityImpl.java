package a.entity.gus06.sys.script1.executor.type.el.r.try1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151117";}
	

	private Service executePart1;
	private Service executePart2;
	private Service wrapping2;
	
	public EntityImpl() throws Exception
	{
		executePart1 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part1");
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
		wrapping2 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping2");
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
			wrapping2.p(new Object[]{context,tag,new Wrap()});
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
			
			try
			{
				executePart1.p(new Map[]{tag,context});
			}
			catch(Exception e)
			{
				pool1.put("e",e);
				executePart2.p(new Map[]{tag,context});
			}
		}
	}
}
