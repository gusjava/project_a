package a.entity.gus06.swing.textcomp.cust.firstedit.backup;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20150426";}


	private Service backuper;

	public EntityImpl() throws Exception
	{backuper = Outside.service(this,"gus06.data.backuper.string");}
	
	
	public void p(Object obj) throws Exception
	{v("default",obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		new Holder(key,comp);
	}
	
	
	
	private void backupText(String key, String text)
	{
		try{backuper.v(key,text);}
		catch(Exception e)
		{Outside.err(this,"backupText(String,String)",e);}
	}

	
	
	
	private class Holder implements DocumentListener
	{
		private JTextComponent comp;
		private String key;
		private boolean done = false;
		
		public Holder(String key, JTextComponent comp)
		{
			this.key = key;
			this.comp = comp;
			comp.getDocument().addDocumentListener(this);
		}
		
		public void changedUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e) {textChanged_();}
		public void removeUpdate(DocumentEvent e) {textChanged_();}
		
		private void textChanged_()
		{
			if(done) return;
			backupText(key,comp.getText());
			done = true;
		}
	}
}
