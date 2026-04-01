package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.clean;

import a.framework.*;
import java.util.Iterator;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250614";}


	private Service show;
	private Service deduplicate;
	private Service mapToLocation;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show.data");
		deduplicate = Outside.service(this,"gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.clean.deduplicate");
		mapToLocation = Outside.service(this,"gus06.sys.filemanagement1.tool.dirmap.findlocation");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		Map md5Map = (Map) o[2];
		
		File dir = (File) mapToLocation.t(new Object[]{engine, selected});
		
		Iterator it = md5Map.keySet().iterator();
		int totalNb = 0;
		int deduplicatedNb = 0;
		
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Map m = (Map) md5Map.get(md5);
			boolean deduplicated = deduplicate.f(new Object[]{dir,m});
			if(deduplicated) deduplicatedNb++; 
			totalNb++;
		}
		String message = "Deduplicated: "+deduplicatedNb+"/"+totalNb;
		show.p(message);
	}
}