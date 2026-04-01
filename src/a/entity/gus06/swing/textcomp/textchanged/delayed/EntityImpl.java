package a.entity.gus06.swing.textcomp.textchanged.delayed;

import java.util.Map;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140723";}

	public static final long DELAY = 200;
	
	private Service getTimer;
	private Timer timer;
	private Map map;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		map = new HashMap();
	}
	
	public synchronized Object t(Object obj) throws Exception
	{
		if(!map.containsKey(obj)) map.put(obj,new TextCompHolder((JTextComponent) obj));
		return map.get(obj);
	}
	
	
	
	private class TextCompHolder extends S1 implements DocumentListener, P, G, V
	{
		private JTextComponent comp;
		private TimerTask task;
		private long delay = DELAY;
		private boolean activated = true;
		
		public TextCompHolder(JTextComponent comp)
		{
			this.comp = comp;
			comp.getDocument().addDocumentListener(this);
		}
		
		public void changedUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e) {textChanged_();}
		public void removeUpdate(DocumentEvent e) {textChanged_();}
		
		private void textChanged_()
		{
			if(task!=null) task.cancel();
			task = new TimerTask(){
				public void run() {perform();}
			};
			timer.schedule(task, delay);
		}
		
		
		private void perform()
		{
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {textChanged();}
			});
		}
		
		private void textChanged()
		{send(this,"textChanged()");}
		
		
		public Object g() throws Exception
		{return comp;}
		
		
		private void setSilent(String text)
		{
			comp.getDocument().removeDocumentListener(this);
			comp.setText(text);
			comp.getDocument().addDocumentListener(this);
		}
		
		

		public void p(Object obj) throws Exception
		{
			if(obj.equals("activate") && !activated)
			{
				activated = true;
				comp.getDocument().addDocumentListener(this);
			}
			else if(obj.equals("disactivate") && activated)
			{
				activated = false;
				comp.getDocument().removeDocumentListener(this);
			}
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("delay"))
			{
				delay = Long.parseLong(""+obj);
				return;
			}
			if(key.equals("silent"))
			{
				setSilent(""+obj);
				return;
			}
			else throw new Exception("Unknown key: "+key);
		}
	}
}