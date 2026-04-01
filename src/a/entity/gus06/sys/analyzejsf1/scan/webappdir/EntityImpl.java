package a.entity.gus06.sys.analyzejsf1.scan.webappdir;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190308";}

	public static final String KEY0_ROOTS = "roots";
	public static final String KEY0_XHTML_FILES = "xhtml-files";
	
	public static final String KEY1_WEBAPP = "webapp";


	private Service scan;

	public EntityImpl() throws Exception
	{
		scan = Outside.service(this,"gus06.dir.perform.scanfiles.byext");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Map roots = (Map) map.get(KEY0_ROOTS);
		File webappRoot = (File) roots.get(KEY1_WEBAPP);
		
		G g = (G) scan.t(new Object[]{webappRoot,"xhtml"});
		File f = (File) g.g();
		
		List list = new ArrayList();
		map.put(KEY0_XHTML_FILES,list);
		
		while(f!=null)
		{
			list.add(f);
			handleFile(map,f);
			f = (File) g.g();
		}
	}
	
	
	
	private void handleFile(Map map, File f)
	{
		
	}
}
