package a.entity.gus06.mysql.socketfactory.watcher.progress2;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170210";}


	private Service buildProgress;
	private Service buildScreen;


	public EntityImpl() throws Exception
	{
		buildProgress = Outside.service(this,"factory#gus06.swing.progressbar.progress1a");
		buildScreen = Outside.service(this,"factory#gus06.swing.panel.screen.image");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder1(obj);
	}
	
	
	
	private class Holder1 implements ActionListener, I, V, R
	{
		private R watcher;
		private V progress;
		private P screen;
		
		private JPanel panel;
		
		public Holder1(Object watcher) throws Exception
		{
			this.watcher = (R) watcher;
			this.progress = (V) buildProgress.g();
			this.screen = (P) buildScreen.g();
			
			panel = new JPanel(new BorderLayout());
			panel.add((JComponent) ((I) screen).i(), BorderLayout.CENTER);
			panel.add((JComponent) ((I) progress).i(), BorderLayout.SOUTH);
			
			((S) watcher).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String s = e.getActionCommand();
			if(s.equals("queryInitialized()")) 
				initProgress(watcher,progress,screen);
			else updateProgress(watcher,progress);}
		
		public Object i() throws Exception
		{return panel;}
		
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
	
	
	
	private void initProgress(R watcher, V progress, P screen)
	{
		try
		{
			long[] info = (long[]) watcher.r("query_info");
			Object image = watcher.r("query_preview");
			
			progress.v("size",""+info[0]);
			progress.v("set",""+info[1]);
			screen.p(image);
		}
		catch(Exception e)
		{Outside.err(this,"initProgress(R,V,P)",e);}
	}
}
