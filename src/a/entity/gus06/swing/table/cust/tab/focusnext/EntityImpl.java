package a.entity.gus06.swing.table.cust.tab.focusnext;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.KeyboardFocusManager;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220601";}

	private Action tabOutAction;

	public EntityImpl() throws Exception
	{
		tabOutAction = new AbstractAction(){
		public void actionPerformed(ActionEvent ae){
	        	KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
		}};
	}
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tabOut");
		table.getActionMap().put("tabOut",tabOutAction);
		
		table.getInputMap(JComponent.WHEN_FOCUSED).remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
	}
}
