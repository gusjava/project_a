package a.entity.gus06.sys.runtask2.input.dir.chooser;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20251212";}

	public static final String TITLE = "Dir Task Chooser";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	
	private Service listChooser;
	private Service annexe;
	private Service manager;
	private Service persister;

	public EntityImpl() throws Exception
	{
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.dialog2");
		annexe = Outside.service(this,"*gus06.sys.runtask2.input.dir.chooser.annexe");
		manager = Outside.service(this,"gus06.sys.runtask2.input.dir.manager");
		persister = Outside.service(this,"gus06.app.persister1");
	}
	
	public Object g() throws Exception
	{
		List keys = new ArrayList((Set) manager.g());
		Collections.sort(keys);
		
		listChooser.v("title",TITLE);
		listChooser.v("width",""+WIDTH);
		listChooser.v("height",""+HEIGHT);
		listChooser.v("annexe",annexe);
		listChooser.v("persistKey",getClass().getName());
		
		String key = (String) listChooser.t(keys);
		if(key==null) return null;
		
		return manager.r("p:"+key);
	}
}
