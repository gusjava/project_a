package a.entity.gus06.sys.filetool.ext.scriptlauncher1.buildlisting;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160928";}
	
	
	private Service buildListing;
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles.forname");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String scriptName = (String) o[1];
		
		List listing = (List) buildListing.t(new Object[]{root,scriptName});
		
		int len = root.getAbsolutePath().length();
		
		Map filesMap = new HashMap();
		for(int i=0;i<listing.size();i++)
		{
			File f = (File) listing.get(i);
			String p = f.getParentFile().getAbsolutePath();
			String p0 = p.substring(len).replace(File.separator,".");
			
			if(p0.startsWith(".")) p0 = p0.substring(1);
			if(p0.endsWith(".")) p0 = p0.substring(0,p0.length()-1);
			
			filesMap.put(p0,f);
		}
		
		return filesMap;
	}
}