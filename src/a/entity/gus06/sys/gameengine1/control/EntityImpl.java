package a.entity.gus06.sys.gameengine1.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G, R, KeyListener, MouseListener, MouseMotionListener {

	public String creationDate() {return "20200515";}


	private Service screenToImage;
	private Service codeToDesc;
	
	private JComponent comp;
	
	private List keys;
	private int[] mouse0;
	private int[] mouse1;
	private int[] mouse;
	
	
	private KeyEvent lastKeyEvent;
	private MouseEvent lastMouseEvent;
	


	public EntityImpl() throws Exception
	{
		screenToImage = Outside.service(this,"gus06.sys.gameengine1.control.mouse.screentoimage");
		codeToDesc = Outside.service(this,"gus06.awt.keyevent.convert.codetodesc");
		keys = new ArrayList();
	}


	
	
	

	public void p(Object obj) throws Exception
	{
		if(comp!=null) throw new Exception("Comp already initialized");
		comp = (JComponent) obj;
		
		comp.addKeyListener(this);
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		comp.setFocusable(true);
	}
	
	

	public Object g() throws Exception
	{return keys;}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("mouse")) return mouse;
		if(key.equals("mouse0")) return mouse0;
		if(key.equals("mouse1")) return mouse1;
		if(key.equals("lastKey")) return codeDesc(lastKeyEvent);
		if(key.equals("lastKeyEvent")) return lastKeyEvent;
		if(key.equals("lastMouseEvent")) return lastMouseEvent;
		
		if(key.equals("keys")) return new String[]{"mouse","mouse0","mouse1","lastKey","lastKeyEvent","lastMouseEvent"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	public void mouseEntered(MouseEvent e)
	{comp.requestFocusInWindow();}
	
	public void mouseExited(MouseEvent e)
	{}
	
	public void keyTyped(KeyEvent e) {}

	

	
	public void mouseClicked(MouseEvent e)
	{
		lastMouseEvent = e;
		mouse = screenToImage(e);
		mouse0 = mouse;
		mouse1 = mouse;
		mouseClicked();
	}
	
	

	public void mousePressed(MouseEvent e)
	{
		comp.requestFocusInWindow();
		lastMouseEvent = e;
		mouse = screenToImage(e);
		mouse0 = mouse;
		mousePressed();
	}
	
	
	

	public void mouseReleased(MouseEvent e)
	{
		lastMouseEvent = e;
		mouse = screenToImage(e);
		mouse1 = mouse;
		mouseReleased();
	}
	
	
	

	public void mouseMoved(MouseEvent e)
	{
		lastMouseEvent = e;
		mouse = screenToImage(e);
		mouseMoved();
	}
	

	public void mouseDragged(MouseEvent e)
	{
		lastMouseEvent = e;
		mouse = screenToImage(e);
		mouseDragged();
	}
	
	
	

	public void keyPressed(KeyEvent e)
	{
		try
		{
			lastKeyEvent = e;
			keys.add(codeDesc(e));
			keyPressed();
		}
		catch(Exception e1)
		{Outside.err(this,"keyPressed(KeyEvent)",e1);}
	}


	

	public void keyReleased(KeyEvent e)
	{
		try
		{
			lastKeyEvent = e;
			String code = codeDesc(e);
			while(keys.contains(code)) keys.remove(code);
			keyReleased();
		}
		catch(Exception e1)
		{Outside.err(this,"keyReleased(KeyEvent)",e1);}
	}



	
	

	private void keyPressed()
	{send(this,"keyPressed()");}

	private void keyReleased()
	{send(this,"keyReleased()");}
	
	private void mouseClicked()
	{send(this,"mouseClicked()");}
	
	private void mousePressed()
	{send(this,"mousePressed()");}
	
	private void mouseReleased()
	{send(this,"mouseReleased()");}
	
	private void mouseMoved()
	{send(this,"mouseMoved()");}
	
	private void mouseDragged()
	{send(this,"mouseDragged()");}
	
	
	
	
	private String codeDesc(KeyEvent e) throws Exception
	{return (String) codeToDesc.t(""+e.getKeyCode());}
	
	
	
	
	private int[] screenToImage(MouseEvent e)
	{
		try{return (int[]) screenToImage.t(new Object[]{e,comp});}
		catch(Exception e1)
		{Outside.err(this,"screenToImage(MouseEvent)",e1);}
		return null;
	}
}
