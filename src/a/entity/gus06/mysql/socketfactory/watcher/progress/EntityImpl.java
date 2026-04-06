package a.entity.gus06.mysql.socketfactory.watcher.progress;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170209";}


	private Service buildProgress;


	public EntityImpl() throws Exception
	{
		buildProgress = Outside.service(this,"factory#gus06.swing.progressbar.progress1a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder1(obj);
	}
	
	
	
	private class Holder1 implements ActionListener, I, V, R
	{
		private V progress;
		private R watcher;
		
		public Holder1(Object watcher) throws Exception
		{
			this.watcher = (R) watcher;
			this.progress = (V) buildProgress.g();
			
			((S) watcher).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{updateProgress(watcher,progress);}
		
		public Object i() throws Exception
		{return ((I)progress).i();}
		
		public void v(String key, Object obj) throws Exception
		{progress.v(key,obj);}
		
		public Object r(String key) throws Exception
		{return watcher.r(key);}
	}
	
	
	
	
	private void updateProgress(R watcher, V progress)
	{
		try
		{
			long[] info = (long[]) watcher.r("query_info");
			if(info==null)
			{
				progress.v("size","0");
				progress.v("set","0");
			}
			else
			{
				progress.v("size",""+info[0]);
				progress.v("set",""+info[1]);
			}
		}
		catch(Exception e)
		{Outside.err(this,"updateProgress(R,V)",e);}
	}
}
