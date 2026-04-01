package a.entity.gus06.appli.usbwebprint.usbkey.holder;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class EntityImpl extends S1 implements Entity, G, R, ActionListener {

	public String creationDate() {return "20140910";}


	private Service watchRoots;
	private Service analyzeMount;

	private File f;
	private Map m;


	public EntityImpl() throws Exception
	{
		watchRoots = Outside.service(this,"gus06.dir.hdd.roots.watcher");
		analyzeMount = Outside.service(this,"gus06.appli.usbwebprint.usbkey.analyze");
		
		watchRoots.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return m;}
	
	
	public Object r(String key) throws Exception
	{
		if(m==null) return null;
		if(!m.containsKey(key))
			throw new Exception("Unknown key: "+key);
		return m.get(key);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("mount()")) handle_mount();
		else if(s.equals("unmount()")) handle_unmount();
	}


	
	
	
	
	private void handle_mount()
	{
		try
		{
			if(f!=null) return;
			File f0 = (File) watchRoots.g();
			
			if(f0==null) return;
			if(!f0.isDirectory()) return;
			
			Map m0 = (Map) analyzeMount.t(f0);
			if(m0==null) return;
			
			if(!f0.exists()) return;
			
			f = f0;
			m = m0;
			
			mounted();
		}
		catch(Exception e)
		{Outside.err(this,"handle_mount()",e);}
	}

	
	

	private void handle_unmount()
	{
		try
		{
			if(f==null) return;
			File f0 = (File) watchRoots.g();
			if(f0==null) return;
			
			if(!f0.equals(f)) return;
			
			f = null;
			m = null;
			
			unmounted();
		}
		catch(Exception e)
		{Outside.err(this,"handle_unmount()",e);}
	}

	
	
	
	
	
	private void mounted()
	{send(this,"mounted()");}
	
	private void unmounted()
	{send(this,"unmounted()");}
}
