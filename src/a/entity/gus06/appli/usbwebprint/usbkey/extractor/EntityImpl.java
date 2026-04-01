package a.entity.gus06.appli.usbwebprint.usbkey.extractor;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class EntityImpl extends S1 implements Entity, Runnable, ActionListener, G, R {

	public String creationDate() {return "20140915";}

	public static final String KEY_ROOT = "root";
	
	public static final String STATE_WAITING = "waiting";
	public static final String STATE_READING = "reading";
	public static final String STATE_INTERRUPTED = "interrupted";
	public static final String STATE_DONE = "done";
	public static final String STATE_EMPTY = "empty";
	public static final String STATE_DISABLED = "disabled";
	public static final String STATE_FAILED = "failed";
	
	

	private Service usbHolder;
	private Service buildListing;
	private Service extractorState;
	
	private Thread t;
	private boolean mounted = false;
	private File rootDir;
	private File file;
	
	private int total = 0;
	private int current = 0;
	


	public EntityImpl() throws Exception
	{
		usbHolder = Outside.service(this,"gus06.appli.usbwebprint.usbkey.holder");
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		extractorState = Outside.service(this,"gus06.appli.usbwebprint.usbkey.extractor.state");
		
		usbHolder.addActionListener(this);
		setState(STATE_WAITING);
	}
	
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("progress")) return new int[]{current,total};
		return usbHolder.r(key);
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("mounted()")) mounted();
		else if(s.equals("unmounted()")) unmounted();
	}
	
	
	
	
	private void mounted()
	{
		mounted = true;
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	private void unmounted()
	{
		mounted = false;
		if(t!=null && t.isAlive()) setState(STATE_INTERRUPTED);
		else setState(STATE_WAITING);
	}
	
	
	
	
	public void run()
	{
		try
		{
			current = 0;
			total = 0;
			
			rootDir = (File) usbHolder.r(KEY_ROOT);
			if(rootDir==null) throw new Exception("Null value for extraction root directory");
			if(!rootDir.exists()) {setState(STATE_INTERRUPTED);return;}
			
			List listing = (List) buildListing.t(rootDir);
			total = listing.size();
			if(total==0) {setState(STATE_EMPTY);return;}
			
			setState(STATE_READING);
			for(Object obj:listing)
			{
				handleFile((File) obj);
				if(!rootDir.exists()) {setState(STATE_INTERRUPTED);return;}
			}
			setState(STATE_DONE);
		}
		catch(Exception e)
		{
			Outside.err(this,"run()",e);
			setState(STATE_FAILED);
		}
	}
	
	
	
	
	
	
	private void setState(String state)
	{
		try{extractorState.p(state);}
		catch(Exception e)
		{Outside.err(this,"setState(String)",e);}
	}
	
	
	
	
	private void handleFile(File file)
	{
		this.file = file;
		current++;
		fileFound();
	}
	
	
	
	
	private void fileFound()
	{send(this,"fileFound()");}
}
