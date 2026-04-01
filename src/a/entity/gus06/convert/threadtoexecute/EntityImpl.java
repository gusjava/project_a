package a.entity.gus06.convert.threadtoexecute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}
	
	
	public Object t(Object obj) throws Exception
	{return new E1((Thread) obj);}
	
	
	private class E1 implements E
	{
		private Thread thread;
		public E1(Thread thread)
		{this.thread = thread;}

		public void e() throws Exception
		{thread.start();}
	}
}
