package a.entity.gus06.swing.scrollpane.scrollsynchronizer.v;

import a.framework.*;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161116";}


	private Service synchBars;
	
	public EntityImpl() throws Exception
	{
		synchBars = Outside.service(this,"gus.x.swing.scrollbar.scrollsynchronizer");
	}

	
	
	public void p(Object obj) throws Exception
	{
		JScrollPane[] scrolls = (JScrollPane[]) obj;
		if(scrolls.length<2) throw new Exception("not enough scroll for synchronizing: "+scrolls.length);
		
		JScrollBar[] bars = new JScrollBar[scrolls.length];
		for(int i=0;i<bars.length;i++)
		bars[i] = scrolls[i].getVerticalScrollBar();
		
		synchBars.p(bars);
	}
}
