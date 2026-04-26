package a.entity.gus06.dir.runtask.text.replaceall.clipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220222";}


	private Service clipboard;
	private Service buildMap;
	private Service buildListing;
	private Service isTextFile;
	private Service replaceAll;
	
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		buildMap = Outside.service(this,"gus06.data.perform.msplit_tn");
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		isTextFile = Outside.service(this,"gus06.file.string.check");
		replaceAll = Outside.service(this,"gus06.file.string.perform.replaceall.map");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String info = (String) clipboard.g();
		if(info==null || info.equals("")) return;
		Map map = (Map) buildMap.t(info);
		
		List listing = (List) buildListing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+listing.size());
		
		for(int i=0;i<listing.size();i++)
		{
			File f = (File) listing.get(i);
			replaceAll(f, map);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	private void replaceAll(File f, Map map) throws Exception
	{
		if(!isTextFile.f(f)) return;
		replaceAll.p(new Object[]{f, map});
	}
}