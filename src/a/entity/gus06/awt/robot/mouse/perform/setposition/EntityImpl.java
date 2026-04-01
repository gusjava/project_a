package a.entity.gus06.awt.robot.mouse.perform.setposition;

import a.framework.*;
import java.awt.Robot;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180301";}


	private Service findInt2;


	private Robot robot;
	
	public EntityImpl() throws Exception
	{
		findInt2 = Outside.service(this,"gus06.find.intarray.len2");
		robot = new Robot();
	}
	
	public void p(Object obj) throws Exception
	{
		int[] p = (int[]) findInt2.t(obj);
		
		/*
		* On repete 8 fois l'appel pour que ca marche ....
		*/
		
		for(int i=0;i<8;i++)
		robot.mouseMove(p[0],p[1]);
	}
}
