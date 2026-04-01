package a.entity.gus06.awt.robot.keyboard.typetext;

import a.framework.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160418";}


	private Service clipboard;

	private Robot robot;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access");
		robot = new Robot();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		for(int i=0;i<s.length();i++)
		handle(s.charAt(i));
	}
	
	
	
	private void handle(char c) throws Exception
	{
		if(c=='a') type(KeyEvent.VK_A);
		else if(c=='b') type(KeyEvent.VK_B);
		else if(c=='c') type(KeyEvent.VK_C);
		else if(c=='d') type(KeyEvent.VK_D);
		else if(c=='e') type(KeyEvent.VK_E);
		else if(c=='f') type(KeyEvent.VK_F);
		else if(c=='g') type(KeyEvent.VK_G);
		else if(c=='h') type(KeyEvent.VK_H);
		else if(c=='i') type(KeyEvent.VK_I);
		else if(c=='j') type(KeyEvent.VK_J);
		else if(c=='k') type(KeyEvent.VK_K);
		else if(c=='l') type(KeyEvent.VK_L);
		else if(c=='m') type(KeyEvent.VK_M);
		else if(c=='n') type(KeyEvent.VK_N);
		else if(c=='o') type(KeyEvent.VK_O);
		else if(c=='p') type(KeyEvent.VK_P);
		else if(c=='q') type(KeyEvent.VK_Q);
		else if(c=='r') type(KeyEvent.VK_R);
		else if(c=='s') type(KeyEvent.VK_S);
		else if(c=='t') type(KeyEvent.VK_T);
		else if(c=='u') type(KeyEvent.VK_U);
		else if(c=='v') type(KeyEvent.VK_V);
		else if(c=='w') type(KeyEvent.VK_W);
		else if(c=='x') type(KeyEvent.VK_X);
		else if(c=='y') type(KeyEvent.VK_Y);
		else if(c=='z') type(KeyEvent.VK_Z);
		
		else if(c=='A') typeShift(KeyEvent.VK_A);
		else if(c=='B') typeShift(KeyEvent.VK_B);
		else if(c=='C') typeShift(KeyEvent.VK_C);
		else if(c=='D') typeShift(KeyEvent.VK_D);
		else if(c=='E') typeShift(KeyEvent.VK_E);
		else if(c=='F') typeShift(KeyEvent.VK_F);
		else if(c=='G') typeShift(KeyEvent.VK_G);
		else if(c=='H') typeShift(KeyEvent.VK_H);
		else if(c=='I') typeShift(KeyEvent.VK_I);
		else if(c=='J') typeShift(KeyEvent.VK_J);
		else if(c=='K') typeShift(KeyEvent.VK_K);
		else if(c=='L') typeShift(KeyEvent.VK_L);
		else if(c=='M') typeShift(KeyEvent.VK_M);
		else if(c=='N') typeShift(KeyEvent.VK_N);
		else if(c=='O') typeShift(KeyEvent.VK_O);
		else if(c=='P') typeShift(KeyEvent.VK_P);
		else if(c=='Q') typeShift(KeyEvent.VK_Q);
		else if(c=='R') typeShift(KeyEvent.VK_R);
		else if(c=='S') typeShift(KeyEvent.VK_S);
		else if(c=='T') typeShift(KeyEvent.VK_T);
		else if(c=='U') typeShift(KeyEvent.VK_U);
		else if(c=='V') typeShift(KeyEvent.VK_V);
		else if(c=='W') typeShift(KeyEvent.VK_W);
		else if(c=='X') typeShift(KeyEvent.VK_X);
		else if(c=='Y') typeShift(KeyEvent.VK_Y);
		else if(c=='Z') typeShift(KeyEvent.VK_Z);
		
		else if(c=='0') type(KeyEvent.VK_NUMPAD0);
		else if(c=='1') type(KeyEvent.VK_NUMPAD1);
		else if(c=='2') type(KeyEvent.VK_NUMPAD2);
		else if(c=='3') type(KeyEvent.VK_NUMPAD3);
		else if(c=='4') type(KeyEvent.VK_NUMPAD4);
		else if(c=='5') type(KeyEvent.VK_NUMPAD5);
		else if(c=='6') type(KeyEvent.VK_NUMPAD6);
		else if(c=='7') type(KeyEvent.VK_NUMPAD7);
		else if(c=='8') type(KeyEvent.VK_NUMPAD8);
		else if(c=='9') type(KeyEvent.VK_NUMPAD9);
		
		else if(c=='$') type(KeyEvent.VK_DOLLAR);
		else if(c=='*') type(KeyEvent.VK_MULTIPLY);
		
		else if(c==':') type(KeyEvent.VK_COLON);
		else if(c==',') type(KeyEvent.VK_COMMA);
		else if(c==';') type(KeyEvent.VK_SEMICOLON);
		else if(c=='.') typeShift(KeyEvent.VK_SEMICOLON);
		
		else if(c=='\t') type(KeyEvent.VK_TAB);
		else if(c=='\n') type(KeyEvent.VK_ENTER);
		else if(c==' ') type(KeyEvent.VK_SPACE);
		
		else if(c=='&') type(KeyEvent.VK_1);
		
		else if(c=='�') type(KeyEvent.VK_2);
		else if(c=='~') typeAltGr(KeyEvent.VK_2);
		
		else if(c=='"') type(KeyEvent.VK_3);
		else if(c=='#') typeAltGr(KeyEvent.VK_3);
		
		else if(c=='\'') type(KeyEvent.VK_4);
		else if(c=='{') typeAltGr(KeyEvent.VK_4);
		
		else if(c=='(') type(KeyEvent.VK_5);
		else if(c=='[') typeAltGr(KeyEvent.VK_5);
		
		else if(c=='-') type(KeyEvent.VK_6);
		else if(c=='|') typeAltGr(KeyEvent.VK_6);
		
		else if(c=='�') type(KeyEvent.VK_7);
		else if(c=='`') typeAltGr(KeyEvent.VK_7);
		
		else if(c=='_') type(KeyEvent.VK_8);
		else if(c=='\\') typeAltGr(KeyEvent.VK_8);
		
		else if(c=='�') type(KeyEvent.VK_9);
		else if(c=='^') typeAltGr(KeyEvent.VK_9);
		
		else if(c=='�') type(KeyEvent.VK_0);
		else if(c=='@') typeAltGr(KeyEvent.VK_0);
		
		// � la place de VK_MINUS ???
//		else if(c==')') type(KeyEvent.VK_MINUS);
//		else if(c==']') typeAltGr(KeyEvent.VK_MINUS);
//		else if(c=='�') typeShift(KeyEvent.VK_MINUS);

		else if(c=='=') type(KeyEvent.VK_EQUALS);
		else if(c=='}') typeAltGr(KeyEvent.VK_EQUALS);
		else if(c=='+') typeShift(KeyEvent.VK_EQUALS);
		
		else if(c=='�') typeAltGr(KeyEvent.VK_E);
		
		else if(c=='<') usePaste("<");
		else if(c=='>') usePaste(">");
		
		else if(c=='�') usePaste("�");
		
		else usePaste(""+c);
//		else throw new Exception("Unsupported character: "+c);
	}
	
	
	private void typeShift(int c) throws Exception
	{
		press(KeyEvent.VK_SHIFT);
		type(c);
		release(KeyEvent.VK_SHIFT);
	}
	
	private void typeAlt(int c) throws Exception
	{
		press(KeyEvent.VK_ALT);
		type(c);
		release(KeyEvent.VK_ALT);
	}
	
	private void typeCtrl(int c) throws Exception
	{
		press(KeyEvent.VK_CONTROL);
		type(c);
		release(KeyEvent.VK_CONTROL);
	}
	
	private void typeAltGr(int c) throws Exception
	{
		press(KeyEvent.VK_CONTROL);
		press(KeyEvent.VK_ALT);
		type(c);
		release(KeyEvent.VK_ALT);
		release(KeyEvent.VK_CONTROL);
	}
	
	
	
	
	private void usePaste(String s) throws Exception
	{
		Object v = clipboard.g();
		putClipboard(s);
		typeCtrl(KeyEvent.VK_V);
		putClipboard(v);
	}
	
	
	
	
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
	
	
	
	private void putClipboard(Object v)
	{
		for(int i=0;i<5;i++)
		try
		{
			sleep1(10);
			clipboard.p(v!=null?v:"");
			return;
		}
		catch(Exception e){}
	}
	
	private void sleep1(long t)
	{
		try{Thread.sleep(t);}
		catch(Exception e){}
	}
}
