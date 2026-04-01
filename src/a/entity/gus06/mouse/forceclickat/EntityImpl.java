package a.entity.gus06.mouse.forceclickat;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170807";}


	private Service mousePos;
	private Service mouseOrder;


	public EntityImpl() throws Exception
	{
		mousePos = Outside.service(this,"gus06.mouse.position");
		mouseOrder = Outside.service(this,"gus06.awt.robot.mouse.order");
	}
	
	public void p(Object obj) throws Exception
	{
		int[] p1 = (int[]) obj;
		int[] p0 = (int[]) mousePos.g();
		
		mouseOrder.v("position",p1);
		mouseOrder.v("click",null);
		mouseOrder.v("position",p0);
	}
}
