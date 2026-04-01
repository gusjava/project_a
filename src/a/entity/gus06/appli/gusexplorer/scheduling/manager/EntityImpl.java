package a.entity.gus06.appli.gusexplorer.scheduling.manager;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, R {

	public String creationDate() {return "20180118";}
	

	private Service scheduler;
	private Service persister;
	private Service executor;
	private Service loadMap;
	private Service propListing;
	
	private File dir;


	public EntityImpl() throws Exception
	{
		scheduler = Outside.service(this,"*gus06.sys.scheduling1.manager");
		persister = Outside.service(this,"gus06.appli.gusexplorer.scheduling.persister");
		executor = Outside.service(this,"gus06.appli.gusexplorer.scheduling.executor");
		loadMap = Outside.service(this,"gus06.appli.gusexplorer.scheduling.loadmap");
		propListing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		
		File dir0 = (File) Outside.resource(this,"defaultdir");
		dir = new File(dir0,"scheduling");
		dir.mkdirs();
		
		File[] files = (File[]) propListing.t(dir);
		if(files.length==0) return;
		
		for(File file:files) loadMap(file);
		
		scheduler.addActionListener(this);
		
		scheduler.v("executor",executor);
		scheduler.v("persister",persister);
		scheduler.e();
	}
	
	
	private void loadMap(File f)
	{
		try
		{
			Map map = (Map) loadMap.t(f);
			if(map!=null) scheduler.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"loadMap(File)",e);}
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dir")) return dir;
		return scheduler.r(key);
	}


	public void actionPerformed(ActionEvent e)
	{updated();}
	
	
	private void updated()
	{send(this,"updated()");}
}
