package a.entity.gus06.awt.bufferedimage.transform.color.hue.random;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}

	
	
	private Service changeHue;
	
	public EntityImpl() throws Exception
	{
		changeHue = Outside.service(this,"gus06.awt.bufferedimage.transform.color.hue");
	}

	public Object t(Object obj) throws Exception
	{
		Float hue = Float.valueOf(randomHue());
		return changeHue.t(new Object[]{obj,hue});
	}
	
	private float randomHue()
	{return (float) Math.random();}
}