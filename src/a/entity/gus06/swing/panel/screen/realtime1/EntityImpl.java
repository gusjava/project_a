package a.entity.gus06.swing.panel.screen.realtime1;

import java.awt.Color;
import javax.swing.JComponent;
import a.framework.*;
import java.util.Timer;

public class EntityImpl implements Entity, P, V, R, I {

	public String creationDate() {return "20180301";}
	
	public static final long LAPSE = 400;
	
	
	private Service getTimer;
	private Timer timer;
	private ScreenJPanel screen;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		screen = new ScreenJPanel(timer);
		screen.setLapse(LAPSE);
	}
	
	
	public Object i() throws Exception
	{return screen;}
	
	
	public void p(Object obj) throws Exception
	{screen.initFunction((H)obj);}
	
	
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("color"))
		{
			screen.setFunctionColor((Color)obj);
			return;
		}
		if(key.equals("background"))
		{
			screen.setScreenColor((Color)obj);
			return;
		}
		if(key.equals("axis"))
		{
			screen.setAxisColor((Color)obj);
			return;
		}
		if(key.equals("lapse"))
		{
			Long lapse = (Long)obj;
			screen.setLapse(lapse.longValue());
			return;
		}
		if(key.equals("range"))
		{
			double[] range = (double[])obj;
			screen.setRange(range);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("label")) return screen.getValueLabel();
		if(key.equals("records")) return screen.getRecords();
		
		if(key.equals("keys")) return new String[]{"label","records"};
		throw new Exception("Unknown key: "+key);
	}

}
