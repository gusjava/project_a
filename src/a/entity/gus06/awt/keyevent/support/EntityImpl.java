package a.entity.gus06.awt.keyevent.support;

import a.framework.*;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;


public class EntityImpl extends S1 implements Entity, G, AWTEventListener {

	public String creationDate() {return "20140730";}

	
	private String code;

	public EntityImpl() throws Exception
	{Toolkit.getDefaultToolkit().addAWTEventListener(this,AWTEvent.KEY_EVENT_MASK);}

	
	public Object g() throws Exception
	{return code;}
	
	
	public void eventDispatched(AWTEvent e)
	{
		if(!(e instanceof KeyEvent)) return;

		KeyEvent ke = (KeyEvent) e;
		if(ke.getID() != KeyEvent.KEY_PRESSED) return;
		
		code = ""+ke.getKeyCode();
		modified();
	}
	
	
	private void modified()
	{send(this,"modified()");}
}
