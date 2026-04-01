package a.entity.gus06.appli.dragontale.player.engine;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20200516";}

	public static final String KEY_FACINGRIGHT = "facingright";
	public static final String VALUE_FACINGRIGHT = "true";
	
	public static final double DDX = 0.3;
	public static final double RDX = 0.4;
	public static final double DX_MAX = 1.6;
	
	public static final double DD_UP = 0.3;
	public static final double DD_DOWN = 0.5;



	private Service control;
	private Service mvtManager;
	private Service playerData;

	public EntityImpl() throws Exception
	{
		control = Outside.service(this,"gus06.sys.gameengine1.control");
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
		playerData = Outside.service(this,"gus06.appli.dragontale.player.data");
	}
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("reset")) {reset();return;}
		throw new Exception("Unknown command: "+s);
	}
	
	
	
	private void reset() throws Exception
	{
		mvtManager.v("position.player",new double[]{100,100});
		mvtManager.v("speed.player",new SpeedProvide());
	}


	
	public void e() throws Exception
	{
	}
	
	
	
	private List keys() throws Exception
	{return (List) control.g();}

	private boolean isUp() throws Exception
	{return keys().contains("UP");}

	private boolean isDown() throws Exception
	{return keys().contains("DOWN");}

	private boolean isLeft() throws Exception
	{return keys().contains("LEFT");}

	private boolean isRight() throws Exception
	{return keys().contains("RIGHT");}
	
	
	
	
	private void put(String key, String value) throws Exception
	{playerData.v(key,value);}
	
	
	
	
	private class SpeedProvide implements G
	{
		private double dx = 0;
		private double dy = 0;
		
		public Object g() throws Exception
		{
			updateSpeed_x();
			updateSpeed_y();
			return new double[]{dx,dy};
		}

		private void updateSpeed_x() throws Exception
		{
			if(isRight())
			{
				dx += DDX;
				if(dx>DX_MAX) dx = DX_MAX;
			}
			else if(isLeft())
			{
				dx -= DDX;
				if(dx<-DX_MAX) dx = -DX_MAX;
			}
			else
			{
				if(dx>0) dx -= Math.min(dx,RDX);
				if(dx<0) dx += Math.min(-dx,RDX);
			}

			if(dx>0) put(KEY_FACINGRIGHT,"true");
			if(dx<0) put(KEY_FACINGRIGHT,"false");
		}
		
		private void updateSpeed_y() throws Exception
		{
			if(isUp()) dy = -DD_UP;
			else if(isDown()) dy = DD_DOWN;
			else dy = 0.1;
		}
	}
}
