package a.entity.gus06.sys.store.t.map.comp.label2;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140909";}

	public static final String KEY_DISPLAY = "display";
	public static final String KEY_UPDATER = "updater";
	public static final String KEY_FORMATTER = "formatter";


	private Service repaint;
	private Service custComp;
	private Service findObj;
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
		custComp = Outside.service(this,"gus06.sys.store.t.map.comp.cust3.map1");
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new JLabel1((Map) obj);
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	private Object obj(String rule) throws Exception
	{return findObj.t(rule);}
	
	
	
	private void updateGui(JLabel label, G g, T t)
	{
		try
		{
			String display = (String) g.g();
			if(t!=null) display = (String) t.t(display);
			repaint.v(display,label);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui(JLabel,G,T)",e);}
	}
	
	
	
	
	
	private class JLabel1 extends JLabel implements ActionListener
	{
		private G g;
		private T t;
		
		public JLabel1(Map map) throws Exception
		{
			custComp.p(new Object[]{this,map});
			
			String display = get(map,KEY_DISPLAY);
			if(display!=null) repaint.v(display,this);
			
			String updater = get(map,KEY_UPDATER);
			if(updater!=null) g = (G) obj(updater);
			
			String formatter = get(map,KEY_FORMATTER);
			if(formatter!=null) t = (T) obj(formatter);
			
			if(g!=null) ((S) g).addActionListener(this);
		}
		
		
		public void actionPerformed(ActionEvent e)
		{updateGui(this,g,t);}
	}
}
