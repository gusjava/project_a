package a.entity.gus06.swing.panel.screen.realtime3;

import java.awt.Color;
import javax.swing.JComponent;
import a.framework.*;
import java.util.Timer;

public class EntityImpl implements Entity, P, V, R,I {

	public String creationDate() {return "20180301";}

	
	private Service getTimer;
	private Service findColor;
	
	private ScreenJPanel screen;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		findColor = Outside.service(this,"gus06.find.color");
		
		Timer timer = (Timer) getTimer.g();
		screen = new ScreenJPanel(timer);
	}
	
	
	public Object i() throws Exception
	{return screen;}
	
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("start")) screen.startRecording();
		else if(s.equals("stop")) screen.stopRecording();
		else throw new Exception("Unknown command: "+s);
	}
	
	
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("type"))
		{
			screen.setType((String)obj);
			return;
		}
		if(key.equals("axisColor"))
		{
			screen.setAxisColor((Color)obj);
			return;
		}
		if(key.equals("lapse"))
		{
			String lapse = (String)obj;
			long lapse_ = Long.parseLong(lapse);
			screen.setLapse(lapse_);
			return;
		}
		if(key.equals("range"))
		{
			double[] range = (double[])obj;
			screen.setRange(range);
			return;
		}
		if(key.contains("#"))
		{
			String[] n = key.split("#",2);
			Color color = (Color) findColor.t(n[0]);
			String name = n[1];
			screen.addFunction(name,color,(H)obj);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("panelInfo")) return screen.getPanelInfo();
		if(key.equals("records")) return screen.getRecords();
		
		if(key.equals("keys")) return new String[]{"panelInfo","records"};
		throw new Exception("Unknown key: "+key);
	}

}
