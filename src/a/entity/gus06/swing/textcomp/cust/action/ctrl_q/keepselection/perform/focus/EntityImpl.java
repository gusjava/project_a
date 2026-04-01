package a.entity.gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.focus;

import a.framework.*;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151106";}


	private Service selection;
	private Service caret;


	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.focus.selection");
		caret = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.focus.caret");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(hasSelection(comp)) selection.p(comp);
		else caret.p(comp);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}
