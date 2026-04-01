package a.entity.gus06.swing.textcomp.synchronizer.text;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200103";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp1 = (JTextComponent) o[0];
		JTextComponent comp2 = (JTextComponent) o[1];
		
		new Holder(comp1,comp2);
	}
	
	
	private class Holder implements DocumentListener
	{
		private JTextComponent comp1;
		private JTextComponent comp2;
		
		private Document doc1;
		private Document doc2;
		
		public Holder(JTextComponent comp1, JTextComponent comp2)
		{
			this.comp1 = comp1;
			this.comp2 = comp2;
			
			this.doc1 = comp1.getDocument();
			this.doc2 = comp2.getDocument();
			
			doc1.addDocumentListener(this);
			doc2.addDocumentListener(this);
		}
		
		public void changedUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e) {synch(e.getDocument());}
		public void removeUpdate(DocumentEvent e) {synch(e.getDocument());}
		
		private void synch(Document doc)
		{
			if(doc==doc1)
			{
				String text = comp1.getText();
				int pos = Math.min(comp2.getCaretPosition(),text.length());
				
				doc2.removeDocumentListener(this);
				comp2.setText(text);
				comp2.setCaretPosition(pos);
				doc2.addDocumentListener(this);
			}
			else if(doc==doc2)
			{
				String text = comp2.getText();
				int pos = Math.min(comp1.getCaretPosition(),text.length());
				
				doc1.removeDocumentListener(this);
				comp1.setText(text);
				comp1.setCaretPosition(pos);
				doc1.addDocumentListener(this);
			}
		}
	}
}
