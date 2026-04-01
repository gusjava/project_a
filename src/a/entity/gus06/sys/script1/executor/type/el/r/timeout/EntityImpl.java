package a.entity.gus06.sys.script1.executor.type.el.r.timeout;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}
	
	
	private Service executePart1;
	private Service executePart2;
	private Service wrapping1;
	private Service perform;
	private Service toLong;
	

	public EntityImpl() throws Exception
	{
		executePart1 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part1");
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
		perform = Outside.service(this,"gus06.thread.timeout.perform");
		toLong = Outside.service(this,"gus06.find.long1.fromint");
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
			
			Long lapse = (Long) toLong.t(main);
			E1 e1 = new E1(new Map[]{tag,context});
			
			boolean ok = perform.f(new Object[]{e1,lapse});
			if(!ok) executePart2.p(new Map[]{tag,context});
		}
	}
	
	
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data) {this.data = data;}
		
		public void e() throws Exception
		{executePart1.p(data);}
	}
}
