package a.entity.gus06.swing.scrollpane.scrollsynchronizer;

import a.framework.*;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20150305";}


	private Service synchBars;

	private boolean vertical = true;
	private boolean horizontal = true;
	
	
	public EntityImpl() throws Exception
	{
		synchBars = Outside.service(this,"gus06.swing.scrollbar.scrollsynchronizer");
	}

	
	
	public void p(Object obj) throws Exception
	{
		JScrollPane[] scrolls = (JScrollPane[]) obj;
		if(scrolls.length<2) throw new Exception("not enough scroll for synchronizing: "+scrolls.length);
		
		if(vertical)
		{
			JScrollBar[] bars = new JScrollBar[scrolls.length];
			for(int i=0;i<bars.length;i++)
			bars[i] = scrolls[i].getVerticalScrollBar();
			synchBars.p(bars);
		}
		if(horizontal)
		{
			JScrollBar[] bars = new JScrollBar[scrolls.length];
			for(int i=0;i<bars.length;i++)
			bars[i] = scrolls[i].getHorizontalScrollBar();
			synchBars.p(bars);
		}
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("vertical"))
		{vertical = Boolean.parseBoolean((String) obj);return;}
		
		if(key.equals("horizontal"))
		{horizontal = Boolean.parseBoolean((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
}
