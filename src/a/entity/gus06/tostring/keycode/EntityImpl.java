package a.entity.gus06.tostring.keycode;

import a.framework.*;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140919";}
	
	
	public Object t(Object obj) throws Exception
	{
		int code = Integer.parseInt((String) obj);
		
		switch(code)
		{
			case KeyEvent.VK_F1:return "F1";
			case KeyEvent.VK_F2:return "F2";
			case KeyEvent.VK_F3:return "F3";
			case KeyEvent.VK_F4:return "F4";
			case KeyEvent.VK_F5:return "F5";
			case KeyEvent.VK_F6:return "F6";
			case KeyEvent.VK_F7:return "F7";
			case KeyEvent.VK_F8:return "F8";
			case KeyEvent.VK_F9:return "F9";
			case KeyEvent.VK_F10:return "F10";
			case KeyEvent.VK_F11:return "F11";
			case KeyEvent.VK_F12:return "F12";
			
			case KeyEvent.VK_A:return "A";
			case KeyEvent.VK_B:return "B";
			case KeyEvent.VK_C:return "C";
			case KeyEvent.VK_D:return "D";
			case KeyEvent.VK_E:return "E";
			case KeyEvent.VK_F:return "F";
			case KeyEvent.VK_G:return "G";
			case KeyEvent.VK_H:return "H";
			case KeyEvent.VK_I:return "I";
			case KeyEvent.VK_J:return "J";
			case KeyEvent.VK_K:return "K";
			case KeyEvent.VK_L:return "L";
			case KeyEvent.VK_M:return "M";
			case KeyEvent.VK_N:return "N";
			case KeyEvent.VK_O:return "O";
			case KeyEvent.VK_P:return "P";
			case KeyEvent.VK_Q:return "Q";
			case KeyEvent.VK_R:return "R";
			case KeyEvent.VK_S:return "S";
			case KeyEvent.VK_T:return "T";
			case KeyEvent.VK_U:return "U";
			case KeyEvent.VK_V:return "V";
			case KeyEvent.VK_W:return "W";
			case KeyEvent.VK_X:return "X";
			case KeyEvent.VK_Y:return "Y";
			case KeyEvent.VK_Z:return "Z";
			
			case KeyEvent.VK_SPACE:return "SPACE";
		}
		return null;
	}
}
