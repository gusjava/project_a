package a.entity.gus.y.quickreplace1.highlight.clear;

import a.framework.*;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Map;
import java.util.HashMap;
import javax.swing.text.*;
import java.awt.Composite;
import java.awt.AlphaComposite;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240714";}

	public static final long LAPSE = 1000;

	private Service getTimer;
	private Timer timer;
	private Map map;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus.y.timer2.unique");
		timer = (Timer) getTimer.g();
		map = new HashMap();
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Object painter = o[1];
		
		if(map.containsKey(comp))
		{
			ClearTask task = (ClearTask) map.get(comp);
			task.cancel();
		}
		removeHighlights(comp,painter);
		
		ClearTask task = new ClearTask(comp,painter);
		map.put(comp,task);
		
		timer.schedule(task,LAPSE);
	}
	
	private class ClearTask extends TimerTask
	{
		private JTextComponent comp;
		private Object painter;
		
		public ClearTask(JTextComponent comp, Object painter)
		{
			this.comp = comp;
			this.painter = painter;
			setAlpha(painter,1.0f);
		}
	
		public void run()
		{
			clearHighlights(comp,painter);
			removeHighlights(comp,painter);
		}
	}
	
	private void clearHighlights(JTextComponent comp, Object painter)
	{
		float val = 1;
		while(val>0)
		{
			val -= 0.05;
			setAlpha(painter,val);
			comp.repaint();
			
			try{Thread.sleep(50);}
			catch(Exception e){}
		}
	}
	
	private void setAlpha(Object painter, float v)
	{
		try
		{
			if(v<0) v=0;
			if(v>1) v=1;
			Composite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,v);
			((V) painter).v("alpha",alpha);
		}
		catch(Exception e)
		{Outside.err(this,"setAlpha(Object,float)",e);}
	}
	
	private void removeHighlights(JTextComponent comp, Object painter)
	{
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		for(int i=0;i<h.length;i++)
		if(h[i].getPainter().equals(painter))
		high.removeHighlight(h[i]);
	}	
}