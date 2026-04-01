package a.entity.gus06.swing.progressbar.progress1.timeleft.indicator;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, P, R, E {

	public String creationDate() {return "20150919";}


	private Service formatDuration;
	private Service formatTime;


	private Object progress;
	
	private long start = -1;
	private long end = -1;
	private long total = -1;
	private long done = -1;
	private long left = -1;
	
	private boolean over = false;
	
	
	


	public EntityImpl() throws Exception
	{
		formatDuration = Outside.service(this,"gus06.string.transform.format.duration.ms.fr");
		formatTime = Outside.service(this,"gus06.string.transform.format.timestamp.fr3");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(progress!=null) throw new Exception("Progress already initialized");
		progress = obj;
		((S) progress).addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{update();}
	
	
	
	private void update()
	{
		try{
			if(over) throw new Exception("Progress have been cancel and is still running");
		
			int v = current();
			int size = size();
			
			if(v==size)
			{
				end();
				return;
			}
			
			if(v==0)
			{
				start();
				return;
			}
			
			done = now() - start;
			double factor = (double) done/(double) v;
			total = (long) (factor*size);
			end = start + total;
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
	
	
	
	
	
	
	public void e() throws Exception
	{
		if(over) throw new Exception("Progress have been cancel and is still running");
		end();
	}
	
	
	private void end()
	{
		over = true;
		end = now();
		total = end-start;
		left = 0;
		done = total;
	}
	
	private void start()
	{
		start = now();
		total = -1;
		end = -1;
		left = -1;
		done = 0;
	}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(!over)
		{
			done = now() - start;
			left = total - done;
		}
		
		if(key.equals("start")) return ""+start;
		if(key.equals("end")) return ""+end;
		if(key.equals("total")) return ""+total;
		if(key.equals("done")) return ""+done;
		if(key.equals("left")) return ""+left;
		
		if(key.equals("display_start")) return formatTime(start);
		if(key.equals("display_end")) return formatTime(end);
		if(key.equals("display_total")) return formatDuration(total);
		if(key.equals("display_done")) return formatDuration(done);
		if(key.equals("display_left")) return formatDuration(left);
		
		if(key.equals("keys")) return new String[]{
			"start","end","total","done","left",
			"display_start","display_end","display_total","display_done","display_left"
		};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	
	
	
	
	private String formatDuration(long v) throws Exception
	{
		if(v==-1) return "?";
		return (String) formatDuration.t(""+v);
	}
	
	private String formatTime(long v) throws Exception
	{
		if(v==-1) return "?";
		return (String) formatTime.t(""+v);
	}
	
	private int current() throws Exception
	{
		Object s = ((R)progress).r("current");
		return Integer.parseInt(""+s);
	}
	
	private int size() throws Exception
	{
		Object s = ((R)progress).r("size");
		return Integer.parseInt(""+s);
	}
	
	private long now()
	{return System.currentTimeMillis();}
}
