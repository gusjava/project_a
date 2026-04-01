package a.entity.gus06.swing.textcomp.wrapper;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141123";}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JTextComponent) obj);}
	
	
	private class Holder extends S1 implements DocumentListener, P, G, I
	{
		private JTextComponent comp;
		
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			comp.getDocument().addDocumentListener(this);
		}
		
		public Object i() throws Exception
		{return comp;}
		
		public Object g() throws Exception
		{return comp.getText();}

		public void p(Object obj) throws Exception
		{
			String value = (String) obj;
			if(value==null) return;
			if(comp.getText().equals(value)) return;
				
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