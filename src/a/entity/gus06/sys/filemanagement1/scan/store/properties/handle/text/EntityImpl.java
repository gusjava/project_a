package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.text;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service getCharset;
	private Service getLineNb;

	public EntityImpl() throws Exception
	{
		getCharset = Outside.service(this,"gus06.file.info.string.charset.asstring.s");
		getLineNb = Outside.service(this,"gus06.file.string.info.linenumber");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsText(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsText(Object engine, Map prop, File file)
	{
		try
		{
			String charset = (String) getCharset.t(file);
			String lineNb = (String) getLineNb.t(file);
			
			prop.put("text.charset",charset);
			prop.put("text.linenb",lineNb);
			
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsText(Object,Map,File)",e);
			prop.put("text.error",e.toString());
		}
	}
}
