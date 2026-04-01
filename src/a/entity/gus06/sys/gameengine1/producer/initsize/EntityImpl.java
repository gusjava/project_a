package a.entity.gus06.sys.gameengine1.producer.initsize;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200515";}

	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;

	public EntityImpl() throws Exception
	{
	}

	public Object g() throws Exception
	{
		return new int[]{WIDTH,HEIGHT};
	}
}
