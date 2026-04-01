package a.entity.gus06.awt.robot.mouse.order;

import a.framework.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20150831";}


	private Service setPosition;
	private Service move;

	private Robot robot;
	
	
	public EntityImpl() throws Exception
	{
		setPosition = Outside.service(this,"gus06.awt.robot.mouse.perform.setposition");
		move = Outside.service(this,"gus06.awt.robot.mouse.perform.move");
		
		robot = new Robot();
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return;
		
		String[] lines = s.split("\n");
		for(String line:lines) handle(line);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("position")) {position(obj);return;}
		if(key.equals("move")) {move(obj);return;}
		if(key.equals("wait")) {wait(obj);return;}
		
		if(key.equals("press")) {pressright();return;}
		if(key.equals("release")) {releaseright();return;}
		if(key.equals("click")) {clickright();return;}
		
		if(key.equals("pressright")) {pressright();return;}
		if(key.equals("releaseright")) {releaseright();return;}
		if(key.equals("clickright")) {clickright();return;}
		if(key.equals("clickright2")) {clickright2();return;}
		
		if(key.equals("pressleft")) {pressleft();return;}
		if(key.equals("releaseleft")) {releaseleft();return;}
		if(key.equals("clickleft")) {clickleft();return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void handle(String s) throws Exception
	{
		s = s.trim();
		if(s.equals("")) return;
		
		if(s.equals("press")) {pressright();return;}
		if(s.equals("release")) {releaseright();return;}
		if(s.equals("click")) {clickright();return;}
		
		if(s.equals("pressright")) {pressright();return;}
		if(s.equals("releaseright")) {releaseright();return;}
		if(s.equals("clickright")) {clickright();return;}
		if(s.equals("clickright2")) {clickright2();return;}
		
		if(s.equals("pressleft")) {pressleft();return;}
		if(s.equals("releaseleft")) {releaseleft();return;}
		if(s.equals("clickleft")) {clickleft();return;}
		
		
		String t0 = t(0,s);
		
		if(t0.equals("position")) {position(t(1,s));return;}
		if(t0.equals("move")) {move(t(1,s));return;}
		if(t0.equals("wait")) {wait(t(1,s));return;}
		
		position(s);
	}
	
	
	
	
	
	
	private void position(Object obj) throws Exception
	{
		int[] a = toPos(obj);
		setPosition.p(a);
	}
	
	
	private void move(Object obj) throws Exception
	{
		int[] a = toPos(obj);
		move.p(a);
	}
	
	private void wait(Object obj) throws Exception
	{
		long lapse = toLong(obj);
		Thread.sleep(lapse);
	}
	
	
	
	
	private void pressright()
	{robot.mousePress(InputEvent.BUTTON1_MASK);}
	
	private void pressleft()
	{robot.mousePress(InputEvent.BUTTON3_MASK);}
	
	
	
	private void releaseright()
	{robot.mouseRelease(InputEvent.BUTTON1_MASK);}
	
	private void releaseleft()
	{robot.mouseRelease(InputEvent.BUTTON3_MASK);}
	
	
	
	private void clickright()
	{pressright();releaseright();}
	
	private void clickleft()
	{pressleft();releaseleft();}
	
	private void clickright2()
	{clickright();clickright();}
	
	
	
	
	
	
	private String t(int i, String s) throws Exception
	{
		String[] n = s.split(" +");
		if(n.length<=i) throw new Exception("Invalid command: "+s);
		return n[i];
	}
	
	private int[] toPos(Object obj) throws Exception
	{
		if(obj instanceof int[]) return (int[]) obj;
		if(obj instanceof String) return stringToPos((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int[] stringToPos(String s) throws Exception
	{
		String[] n = s.split(":");
		if(n.length!=2)  throw new Exception("Invalid position: "+s);
		return new int[]{int_(n[0]),int_(n[1])};
	}
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return ((Long) obj).longValue();
		if(obj instanceof String) return Long.parseLong((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}
