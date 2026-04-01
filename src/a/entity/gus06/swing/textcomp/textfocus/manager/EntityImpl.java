package a.entity.gus06.swing.textcomp.textfocus.manager;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20151107";}

	private Service back;
	private Map map;

	public EntityImpl() throws Exception
	{
		back = Outside.service(this,"gus06.swing.textcomp.textfocus.back");
		map = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return holder((JTextComponent) obj);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		return holder((JTextComponent) obj).g()!=null;
	}
	
	
	
	private Holder holder(JTextComponent comp) throws Exception
	{
		if(!map.containsKey(comp))
			map.put(comp,new Holder(comp));
		return (Holder) map.get(comp);
	}
	
	
	private class Holder extends S1 implements P, G, E, V
	{
		private JTextComponent comp;
		private List queue;
		private boolean focus = false;
		
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			queue = new ArrayList();
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("focus")) {changeFocus((String) obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			performForward((List) o[0],(String) o[1]);
		}
		
		
		public void e() throws Exception
		{
			performBack();
		}
		
		
		public Object g() throws Exception
		{
			return focus ? queue : null;
		}
		
		
		private void performBack() throws Exception
		{
			if(!focus || queue.isEmpty()) return;
			List l = (List) queue.remove(queue.size()-1);
			back.p(new Object[]{comp,l});
			focusChanged();
		}
		
		
		private void performForward(List l, String text) throws Exception
		{
			if(!focus) return;
			queue.add(l);
			comp.setText(text);
			focusChanged();
		}
		
		
		private void changeFocus(String s)
		{
			if(s.equals("shift"))
			{
				focus = !focus;
				focusChanged();
			}
			else
			{
				boolean v = Boolean.parseBoolean(s);
				if(focus==v) return;
				focus = v;
				focusChanged();
			}
		}
		
		
		private void focusChanged()
		{send(this,"focusChanged()");}
	}
}
