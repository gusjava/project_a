package a.entity.gus06.dir.hdd.roots.watcher;

import a.framework.*;
import java.io.File;
import java.util.*;


public class EntityImpl extends S1 implements Entity, G {

	public String creationDate() {return "20140910";}

	public static final long LAPSE = 1000;
	

	private Timer timer;
	private Set roots;
	
	private File newRoot;



	public EntityImpl() throws Exception
	{
		roots = filesToSet(File.listRoots());
		
		TimerTask task = new TimerTask(){
			public void run() {update();}
		};
		
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	public Object g() throws Exception
	{return newRoot;}


	private void mount()
	{send(this,"mount()");}
	
	private void unmount()
	{send(this,"unmount()");}
	
	
	
	
	
	private void update()
	{
		Set roots_ = filesToSet(File.listRoots());

		if(roots_.size() > roots.size()) //mount
		{
			newRoot = searchMount(roots_);
			roots = roots_;
			mount();
		}
		else if(roots_.size() < roots.size()) //unmount
		{
			newRoot = searchUnmount(roots_);
			roots = roots_;
			unmount();
		}
		else
		{
			newRoot = null;
			roots = roots_;
		}
	}



	
	
	private File searchMount(Set roots_)
	{
		Iterator it = roots_.iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
			if(!roots.contains(f)) return f;
		}
		return null;
	}

	
	private File searchUnmount(Set roots_)
	{
		Iterator it = roots.iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
			if(!roots_.contains(f)) return f;
		}
		return null;
	}


	
	
	private Set filesToSet(File[] r)
	{return new HashSet(Arrays.asList(r));}
}
