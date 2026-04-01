package a.entity.gus06.awt.robot.mouse.perform.move;

import a.framework.*;
import java.awt.Point;
import java.awt.MouseInfo;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180301";}


	private Service findInt2;
	private Service setPosition;
	
	public EntityImpl() throws Exception
	{
		findInt2 = Outside.service(this,"gus06.find.intarray.len2");
		setPosition = Outside.service(this,"gus06.awt.robot.mouse.perform.setposition");
	}
	
	public void p(Object obj) throws Exception
	{
		int[] p = (int[]) findInt2.t(obj);
		
		Point p0 = MouseInfo.getPointerInfo().getLocation();
		int[] p1 = new int[]{p0.x+p[0],p0.y+p[1]};
		
		setPosition.p(p1);
	}
}
