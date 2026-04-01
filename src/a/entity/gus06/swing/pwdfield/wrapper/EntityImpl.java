package a.entity.gus06.swing.pwdfield.wrapper;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JPasswordField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150301";}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JPasswordField) obj);}
	
	
	private class Holder extends S1 implements DocumentListener, P, G, I
	{
		private JPasswordField comp;
		
		public Holder(JPasswordField comp)
		{
			this.comp = comp;
			comp.getDocument().addDocumentListener(this);
		}
		
		public Object i() throws Exception
		{return comp;}
		
		public Object g() throws Exception
		{return new String(comp.getPassword());}

		public void p(Object obj) throws Exception
		{
			String value = (String) obj;
			if(value==null) return;
			if(new String(comp.getPassword()).equals(value)) return;
			
			setActivated(false);
			comp.setText(value);
			setActivated(true);
			dataModified();
		}
		
		public void changedUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e) {dataEdited();dataModified();}
		public void removeUpdate(DocumentEvent e) {dataEdited();dataModified();}
		
		private void dataModified()
		{send(this,"dataModified()");}
		
		private void dataEdited()
		{send(this,"dataEdited()");}
	}
}