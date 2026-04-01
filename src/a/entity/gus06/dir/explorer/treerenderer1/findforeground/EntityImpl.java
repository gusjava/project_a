package a.entity.gus06.dir.explorer.treerenderer1.findforeground;

import a.framework.*;
import java.util.Map;
import java.util.Hashtable;
import java.io.File;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180507";}

	public static final int NB_LIMIT = 1000;
	public static final long LAPSE = 1000;
	
	public static final Color COLOR_EMPTY = Color.RED;
	public static final Color COLOR_BIG = Color.GREEN;



	private Service getTimer;
	private Timer timer;
	private Map cache;

	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		cache = new HashMap();
		
		TimerTask task = new TimerTask() {
			public void run() {resetCache();}
		};
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	public synchronized Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		String path = f.getAbsolutePath();
		
		if(!cache.containsKey(path))
			cache.put(path,build(f));
			
		return cache.get(path);
	}
	
	
	private synchronized void resetCache()
	{
		cache.clear();
	}
	
	
	private Color build(File f)
	{
		if(!f.exists()) return Color.GRAY;
		
		if(f.isDirectory())
		{
			String[] nn = f.list();
			if(nn==null) return COLOR_EMPTY;
			if(nn.length>=NB_LIMIT) return COLOR_BIG;
			if(!hasFiles(f)) return COLOR_EMPTY;
		}
		if(f.isFile())
		{
			if(f.length()==0) return COLOR_EMPTY;
		}
		
		return Color.BLACK;
	}
	
	
	private boolean hasFiles(File dir)
	{
		File[] f = dir.listFiles();
		if(f==null || f.length==0) return false;
		
		for(int i=0;i<f.length;i++)
		{
			if(f[i].isFile()) return true;
			if(f[i].isDirectory() && hasFiles(f[i])) return true;
		}
		return false;
	}
}
