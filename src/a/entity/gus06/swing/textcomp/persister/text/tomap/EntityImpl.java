package a.entity.gus06.swing.textcomp.persister.text.tomap;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170722";}


	private Service buildDelayed;


	public EntityImpl() throws Exception
	{
		buildDelayed = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Map map = (Map) o[1];
		String key = (String) o[2];
		
		if(map.containsKey(key))
		{
			String text = (String) map.get(key);
			comp.setText(text);
		}
		new Holder(comp,map,key);
	}
	
	
	
	private class Holder implements ActionListener
	{
		private JTextComponent comp;
		private Map map;
		private String key;
		
		public Holder(JTextComponent comp, Map map, String key) throws Exception
		{
			this.comp = comp;
			this.map = map;
			this.key = key;
			
			S delayed = (S) buildDelayed.t(comp);
			delayed.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{save(comp,map,key);}
	}
	
	
	private void save(JTextComponent comp, Map map, String key)
	{
		try
		{
			String s = comp.getText();
			map.put(key,s);
		}
		catch(Exception e)
		{Outside.err(this,"save(JTextComponent,Map,String)",e);}
	}
}
