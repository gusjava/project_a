package a.entity.gus06.awt.robot.keyboard.order;

import a.framework.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160418";}


	private Robot robot;
	
	public EntityImpl() throws Exception
	{robot = new Robot();}
	
	
	public void p(Object obj) throws Exception
	{handle((String) obj);}
	
	
	
	private void handle(String s) throws Exception
	{
		if(s.startsWith("press "))	{press1(s);return;}
		if(s.startsWith("release "))	{release1(s);return;}
		if(s.startsWith("type "))	{type1(s);return;}
		
		if(s.startsWith("ctrl "))	{ctrl(s);return;}
		if(s.startsWith("alt "))	{alt(s);return;}
		if(isUpperCaseLetter(s))	{upper(s);return;}
		
		type(findCode(s));
	}
	
	
	
	private int findCode(String s) throws Exception
	{
		if(s.equals("tab")) return KeyEvent.VK_TAB;
		if(s.equals("\t")) return KeyEvent.VK_TAB;
		
		if(s.equals("enter")) return KeyEvent.VK_ENTER;
		if(s.equals("\n")) return KeyEvent.VK_ENTER;
		
		if(s.equals("space")) return KeyEvent.VK_SPACE;
		if(s.equals(" ")) return KeyEvent.VK_SPACE;
		
		if(s.equals("f1")) return KeyEvent.VK_F1;
		if(s.equals("f2")) return KeyEvent.VK_F2;
		if(s.equals("f3")) return KeyEvent.VK_F3;
		if(s.equals("f4")) return KeyEvent.VK_F4;
		if(s.equals("f5")) return KeyEvent.VK_F5;
		if(s.equals("f6")) return KeyEvent.VK_F6;
		if(s.equals("f7")) return KeyEvent.VK_F7;
		if(s.equals("f8")) return KeyEvent.VK_F8;
		if(s.equals("f9")) return KeyEvent.VK_F9;
		if(s.equals("f10")) return KeyEvent.VK_F10;
		if(s.equals("f11")) return KeyEvent.VK_F11;
		if(s.equals("f12")) return KeyEvent.VK_F12;
		
		if(s.equals("a")) return KeyEvent.VK_A;
		if(s.equals("b")) return KeyEvent.VK_B;
		if(s.equals("c")) return KeyEvent.VK_C;
		if(s.equals("d")) return KeyEvent.VK_D;
		if(s.equals("e")) return KeyEvent.VK_E;
		if(s.equals("f")) return KeyEvent.VK_F;
		if(s.equals("g")) return KeyEvent.VK_G;
		if(s.equals("h")) return KeyEvent.VK_H;
		if(s.equals("i")) return KeyEvent.VK_I;
		if(s.equals("j")) return KeyEvent.VK_J;
		if(s.equals("k")) return KeyEvent.VK_K;
		if(s.equals("l")) return KeyEvent.VK_L;
		if(s.equals("m")) return KeyEvent.VK_M;
		if(s.equals("n")) return KeyEvent.VK_N;
		if(s.equals("o")) return KeyEvent.VK_O;
		if(s.equals("p")) return KeyEvent.VK_P;
		if(s.equals("q")) return KeyEvent.VK_Q;
		if(s.equals("r")) return KeyEvent.VK_R;
		if(s.equals("s")) return KeyEvent.VK_S;
		if(s.equals("t")) return KeyEvent.VK_T;
		if(s.equals("u")) return KeyEvent.VK_U;
		if(s.equals("v")) return KeyEvent.VK_V;
		if(s.equals("w")) return KeyEvent.VK_W;
		if(s.equals("x")) return KeyEvent.VK_X;
		if(s.equals("y")) return KeyEvent.VK_Y;
		if(s.equals("z")) return KeyEvent.VK_Z;
		
		throw new Exception("Unsupported keyboard order: "+s);
	}
	
	
	public static final int KEY_MAJ = 16;
	public static final int KEY_CTRL = 17;
	
	
	
	
	private void press1(String s) throws Exception
	{
		int code = Integer.parseInt(t1(s));
		press(code);
	}
	
	private void release1(String s) throws Exception
	{
		int code = Integer.parseInt(t1(s));
		release(code);
	}
	
	private void type1(String s) throws Exception
	{
		int code = Integer.parseInt(t1(s));
		type(code);
	}
	
	
	
	
	private void ctrl(String s) throws Exception
	{
		press(KEY_CTRL);
		handle(s.substring(5));
		release(KEY_CTRL);
	}
	
	private void alt(String s) throws Exception
	{
		press(KeyEvent.VK_ALT);
		handle(t1(s));
		release(KeyEvent.VK_ALT);
	}
	
	private void upper(String s) throws Exception
	{
		press(KEY_MAJ);
		handle(t1(s.toLowerCase()));
		release(KEY_MAJ);
	}
	
	
	private boolean isUpperCaseLetter(String s)
	{
		if(s.length()!=1) return false;
		return Character.isUpperCase(s.charAt(0));
	}
	
	
	private String t1(String s)
	{return s.split(" +",2)[1];}
	
	
	
	
	
	private void type(int c) throws Exception
	{
		press(c);
		release(c);
	}
	
	private void press(int c) throws Exception
	{
		try{robot.keyPress(c);}
		catch(IllegalArgumentException e)
		{throw new Exception("keyPress failed with code="+c,e);}
	}
	
	private void release(int c) throws Exception
	{
		try{robot.keyRelease(c);}
		catch(IllegalArgumentException e)
		{throw new Exception("keyReleased failed with code="+c,e);}
	}
}
