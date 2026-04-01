package a.entity.gus06.swing.list.cust3.tooltip1;

import a.framework.*;
import javax.swing.JList;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191218";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JList list = (JList) o[0];
		T t = (T) o[1];
		
		new TooltipHandler(list,t);
	}
	
	private class TooltipHandler extends MouseMotionAdapter
	{
		private JList list;
		private T t;
		
		public TooltipHandler(JList list, T t)
		{
			super();
			this.list = list;
			this.t = t;
			list.addMouseMotionListener(this);
		}
		
		public void mouseMoved(MouseEvent evt)
		{
			Point p = evt.getPoint();
			int index = list.locationToIndex(p);
			if(index<0) return;
			
			String tooltip = build(t,index);
			
			if(tooltip!=null)
			list.setToolTipText(tooltip);
			else list.setToolTipText(null);
		}
	}
	
	
	private String build(T t, int index)
	{
		try{return (String) t.t(index);}
		catch(Exception e)
		{Outside.err(this,"build(T,int)",e);}
		return null;
	}

}
