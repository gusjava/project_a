package a.entity.gus06.swing.textcomp.autocopy.manager;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.text.JTextComponent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20190804";}


	private Service clipboard;
	private Map map;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		map = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return holder((JTextComponent) obj);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		return holder((JTextComponent) obj).f(null);
	}
	
	
	
	private void copyText(JTextComponent comp)
	{
		try
		{
			String text = comp.getSelectedText();
			if(text==null || text.equals("")) return;
			clipboard.p(text);
		}
		catch(Exception e)
		{Outside.err(this,"copyText(JTextComponent)",e);}
	}
	
	
	
	
	private Holder holder(JTextComponent comp) throws Exception
	{
		if(!map.containsKey(comp))
			map.put(comp,new Holder(comp));
		return (Holder) map.get(comp);
	}
	
	
	private class Holder extends S1 implements V, F, CaretListener
	{
		private JTextComponent comp;
		private boolean autoCopy = false;
		
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			comp.addCaretListener(this);
		}
		
		public void caretUpdate(CaretEvent e)
		{
			if(autoCopy) perform();
		}
		
		private void perform()
		{copyText(comp);}
		
		
		public boolean f(Object obj) throws Exception
		{return autoCopy;}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("autoCopy")) {changeAutoCopy((String) obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		private void changeAutoCopy(String s)
		{
			if(s.equals("shift"))
			{
				autoCopy = !autoCopy;
				autoCopyChanged();
			}
			else
			{
				boolean v = Boolean.parseBoolean(s);
				if(autoCopy==v) return;
				autoCopy = v;
				autoCopyChanged();
			}
		}
		
		private void autoCopyChanged()
		{send(this,"autoCopyChanged()");}
	}
}
