package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.pdf;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service getPdfInfoMap;
	private Service safe;
	
	private T safeT;

	public EntityImpl() throws Exception
	{
		getPdfInfoMap = Outside.service(this,"gus06.file.pdf.infomap");
		safe = Outside.service(this,"gus06.feature.wrap.t.safe");
		
		safeT = (T) safe.t(getPdfInfoMap);
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsPdf(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsPdf(Object engine, Map prop, File file)
	{
		try
		{
			Map pdfProps = (Map) safeT.t(file);
			Iterator it = pdfProps.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) pdfProps.get(key);
				prop.put("pdf."+key.toLowerCase(),value);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsPdf(Object,Map,File)",e);
			prop.put("pdf.error",e.toString());
		}
	}
}
