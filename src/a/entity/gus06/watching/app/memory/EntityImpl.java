package a.entity.gus06.watching.app.memory;

import a.framework.*;
import java.awt.Font;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import java.text.NumberFormat;
import java.util.Locale;

public class EntityImpl extends S1 implements Entity, R, G, H {

	public String creationDate() {return "20190625";}

	public final static long LAPSE = 100;
	public final static NumberFormat NF = NumberFormat.getNumberInstance(Locale.FRENCH);


	private Service find;
	private Service getTimer;
	
	private Timer timer;
	private TimerTask task;
	private Set updators;
	
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.app.win.tasklist.mem");
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		updators = new HashSet();
		
		task = new TimerTask(){public void run(){update();}};
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	
	public Object g() throws Exception
	{return find.g();}
	
	
	public double h(double value) throws Exception
	{return find.h(value);}



	public Object r(String key) throws Exception
	{
		if(key.equals("label")) return buildLabel();
		if(key.equals("keys")) return new String[]{"label"};
				
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private JLabel buildLabel()
	{
		JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(Font.PLAIN));
		updators.add(new LabelUpdator(label));
		return label;
	}
	



	private boolean broken = false;
	
	private void update()
	{
		try
		{	if(broken) return;
			if(updators.isEmpty()) return;
			
			Long value = (Long) find.g();
			Iterator it = updators.iterator();
			while(it.hasNext()) ((P) it.next()).p(value);
			send(this,"update()");
		}
		catch(Exception e)
		{
			Outside.err(this,"update()",e);
			broken = true;
		}
	}





	private class LabelUpdator implements P
	{
		private JLabel label;
		public LabelUpdator(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			Long value = (Long) obj;
			label.setText(" "+NF.format(value)+" Kb");
		}
	}
}
