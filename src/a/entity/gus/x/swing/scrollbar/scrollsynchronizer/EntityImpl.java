package a.entity.gus.x.swing.scrollbar.scrollsynchronizer;

import a.framework.*;
import javax.swing.BoundedRangeModel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20161116";}
	
	public void p(Object obj) throws Exception
	{
		JScrollBar[] bars = (JScrollBar[]) obj;
		if(bars.length<2) throw new Exception("not enough scrollbars for synchronizing: "+bars.length);		
		new SynchScroll(bars);
	}
	
	private class SynchScroll implements ChangeListener
	{
		private BoundedRangeModel[] models;
		
		public SynchScroll(JScrollBar[] bars)
		{
			models = new BoundedRangeModel[bars.length];
			for(int i=0;i<bars.length;i++)
			{
				models[i] = bars[i].getModel();
				models[i].addChangeListener(this);
			}
		}

		public void stateChanged(ChangeEvent e)
		{
			for(int i=0;i<models.length;i++)
			if(models[i].getMaximum()<=0)return;
			
			BoundedRangeModel modelSrc = (BoundedRangeModel)e.getSource();
			int value = modelSrc.getValue();
			
			for(int i=0;i<models.length;i++)
			models[i].setValue(value);
		}
	}
}
