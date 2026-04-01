package a.entity.gus06.sys.script1.executor.type.el.op.execute;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20150831";}


	private Service wrapping1;
	private Service handleRunnable;

	public EntityImpl() throws Exception
	{
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
		handleRunnable = Outside.service(this,"gus06.convert.runnabletoexecute");
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
			
			convertToE(main).e();
		}
	}
	
	private E convertToE(Object main) throws Exception
	{
		if(main==null) throw new Exception("Could not execute null object");
		if(main instanceof E) return (E) main;
		if(main instanceof Runnable) return (E) handleRunnable.t(main);
		throw new Exception("Could not execute object of type: "+main.getClass().getName());
	}
}