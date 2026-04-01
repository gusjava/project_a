package a.entity.gus06.watching.jvm.memory;

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

public class EntityImpl extends S1 implements Entity, R {

	public String creationDate() {return "20190209";}

	public final static long LAPSE = 100;
	public final static NumberFormat NF = NumberFormat.getNumberInstance(Locale.FRENCH);


	private Runtime r;
	private Service getTimer;
	private Timer timer;
	private TimerTask task;
	
	private H freeMemory;
	private H maxMemory;
	private H totalMemory;
	private H usedMemory;
	
	private long free(){return r.freeMemory();}
	private long max(){return r.maxMemory();}
	private long total(){return r.totalMemory();}
	private long used(){return r.totalMemory()-r.freeMemory();}
	
	private Set updators;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		r = Runtime.getRuntime();
		updators = new HashSet();
		
		task = new TimerTask(){public void run(){update();}};
		timer.schedule(task,new Date(),LAPSE);
		
		freeMemory = new H(){
			public double h(double value) throws Exception
			{return (double) free();}};
		maxMemory = new H(){
			public double h(double value) throws Exception
			{return (double) max();}};
		totalMemory = new H(){
			public double h(double value) throws Exception
			{return (double) total();}};
		usedMemory = new H(){
			public double h(double value) throws Exception
			{return (double) used();}};
	}



	public Object r(String key) throws Exception
	{
		if(key.equals("freeMemory")) return freeMemory;
		if(key.equals("maxMemory")) return maxMemory;
		if(key.equals("totalMemory")) return totalMemory;
		if(key.equals("usedMemory")) return usedMemory;
		
		if(key.equals("label1Free")) return buildLabel1Free();
		if(key.equals("label2Free")) return buildLabel2Free();
		
		if(key.equals("label1Used")) return buildLabel1Used();
		if(key.equals("label2Used")) return buildLabel2Used();
		
		if(key.equals("label1All")) return buildLabel1All();
		if(key.equals("label2All")) return buildLabel2All();
		
		if(key.equals("keys"))
			return new String[]{
				"freeMemory","maxMemory",
				"totalMemory","usedMemory",
				"label1Free","label2Free",
				"label1Used","label2Used",
				"label1All","label2All"};
				
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private JLabel buildLabel()
	{
		JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(Font.PLAIN));
		return label;
	}
	
	private JLabel buildLabel1Free()
	{
		JLabel label = buildLabel();
		updators.add(new LabelUpdator1Free(label));
		return label;
	}
	
	private JLabel buildLabel2Free()
	{
		JLabel label = buildLabel();
		updators.add(new LabelUpdator2Free(label));
		return label;
	}
	
	private JLabel buildLabel1Used()
	{
		JLabel label = buildLabel();
		updators.add(new LabelUpdator1Used(label));
		return label;
	}
	
	private JLabel buildLabel2Used()
	{
		JLabel label = buildLabel();
		updators.add(new LabelUpdator2Used(label));
		return label;
	}
	
	private JLabel buildLabel1All()
	{
		JLabel label = buildLabel();
		updators.add(new LabelUpdator1All(label));
		return label;
	}
	
	private JLabel buildLabel2All()
	{
		JLabel label = buildLabel();
		updators.add(new LabelUpdator2All(label));
		return label;
	}
	



	private boolean broken = false;
	
	private void update()
	{
		try
		{	if(broken) return;
			if(updators.isEmpty()) return;
			
			long[] values = new long[]{free(),max(),total()};
			Iterator it = updators.iterator();
			while(it.hasNext()) ((P) it.next()).p(values);
			send(this,"update()");
		}
		catch(Exception e)
		{
			Outside.err(this,"update()",e);
			broken = true;
		}
	}





	private class LabelUpdator1Free implements P
	{
		private JLabel label;
		public LabelUpdator1Free(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			long[] values = (long[]) obj;
			label.setText(" free:"+NF.format(values[0]));
		}
	}
	
	private class LabelUpdator2Free implements P
	{
		private JLabel label;
		public LabelUpdator2Free(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			long[] values = (long[]) obj;
			label.setText(" "+formatKb(values[0]));
		}
	}
	
	
	
	
	private class LabelUpdator1Used implements P
	{
		private JLabel label;
		public LabelUpdator1Used(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			long[] values = (long[]) obj;
			label.setText(" used:"+NF.format(values[2]-values[0]));
		}
	}
	
	private class LabelUpdator2Used implements P
	{
		private JLabel label;
		public LabelUpdator2Used(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			long[] values = (long[]) obj;
			label.setText(" "+formatKb(values[2]-values[0]));
		}
	}
	
	
	
	
	
	private class LabelUpdator1All implements P
	{
		private JLabel label;
		public LabelUpdator1All(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			long[] values = (long[]) obj;
			label.setText(" "+display1All(values));
		}
	}
	
	private class LabelUpdator2All implements P
	{
		private JLabel label;
		public LabelUpdator2All(JLabel label)
		{this.label=label;}
		
		public void p(Object obj) throws Exception
		{
			long[] values = (long[]) obj;
			label.setText(" "+display2All(values));
		}
	}
	
	
	
	
	
	private String display1All(long[] values)
	{return "free:"+values[0]+"  max:"+values[1]+"  total:"+values[2];}
	
	private String display2All(long[] values)
	{return "f:"+values[0]+"  m:"+values[1]+"  t:"+values[2];}
	
	
	private String formatKb(long value)
	{return NF.format((long) value/1000L)+" Kb";}
}
