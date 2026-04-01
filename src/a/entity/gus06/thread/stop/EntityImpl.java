package a.entity.gus06.thread.stop;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180117";}


	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) obj;
		t.stop();
	}
}
