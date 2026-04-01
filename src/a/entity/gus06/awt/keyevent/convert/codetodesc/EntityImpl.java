package a.entity.gus06.awt.keyevent.convert.codetodesc;

import java.awt.event.KeyEvent;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200515";}


	public EntityImpl() throws Exception
	{
	}


	public Object t(Object obj) throws Exception
	{
		int code = Integer.parseInt((String)obj);
		
		switch (code) {
			case KeyEvent.VK_0: return "0";
			case KeyEvent.VK_1: return "1";
			case KeyEvent.VK_2: return "2";
			case KeyEvent.VK_3: return "3";
			case KeyEvent.VK_4: return "4";
			case KeyEvent.VK_5: return "5";
			case KeyEvent.VK_6: return "6";
			case KeyEvent.VK_7: return "7";
			case KeyEvent.VK_8: return "8";
			case KeyEvent.VK_9: return "9";
			
			case KeyEvent.VK_F1: return "F1";
			case KeyEvent.VK_F2: return "F2";
			case KeyEvent.VK_F3: return "F3";
			case KeyEvent.VK_F4: return "F4";
			case KeyEvent.VK_F5: return "F5";
			case KeyEvent.VK_F6: return "F6";
			case KeyEvent.VK_F7: return "F7";
			case KeyEvent.VK_F8: return "F8";
			case KeyEvent.VK_F9: return "F9";
			case KeyEvent.VK_F10: return "F10";
			case KeyEvent.VK_F11: return "F11";
			case KeyEvent.VK_F12: return "F12";

			case KeyEvent.VK_UP: return "UP";
			case KeyEvent.VK_DOWN: return "DOWN";
			case KeyEvent.VK_LEFT: return "LEFT";
			case KeyEvent.VK_RIGHT: return "RIGHT";
			
			case KeyEvent.VK_TAB: return "TAB";
			case KeyEvent.VK_SPACE: return "SPACE";
			case KeyEvent.VK_ENTER: return "ENTER";
			case KeyEvent.VK_DELETE: return "DELETE";
			case KeyEvent.VK_INSERT: return "INSERT";
			case KeyEvent.VK_ESCAPE: return "ESCAPE";
			case KeyEvent.VK_PRINTSCREEN: return "PRINTSCREEN";
			case KeyEvent.VK_CONTROL: return "CTRL";
			case KeyEvent.VK_ALT: return "ALT";
			case KeyEvent.VK_ALT_GRAPH: return "ALTGR";
			case KeyEvent.VK_SHIFT: return "SHIFT";
			
			case KeyEvent.VK_A: return "A";
			case KeyEvent.VK_B: return "B";
			case KeyEvent.VK_C: return "C";
			case KeyEvent.VK_D: return "D";
			case KeyEvent.VK_E: return "E";
			case KeyEvent.VK_F: return "F";
			case KeyEvent.VK_G: return "G";
			case KeyEvent.VK_H: return "H";
			case KeyEvent.VK_I: return "I";
			case KeyEvent.VK_J: return "J";
			case KeyEvent.VK_K: return "K";
			case KeyEvent.VK_L: return "L";
			case KeyEvent.VK_M: return "M";
			case KeyEvent.VK_N: return "N";
			case KeyEvent.VK_O: return "O";
			case KeyEvent.VK_P: return "P";
			case KeyEvent.VK_Q: return "Q";
			case KeyEvent.VK_R: return "R";
			case KeyEvent.VK_S: return "S";
			case KeyEvent.VK_T: return "T";
			case KeyEvent.VK_U: return "U";
			case KeyEvent.VK_V: return "V";
			case KeyEvent.VK_W: return "W";
			case KeyEvent.VK_X: return "X";
			case KeyEvent.VK_Y: return "Y";
			case KeyEvent.VK_Z: return "Z";

			default:return null;
		}
	}
}
