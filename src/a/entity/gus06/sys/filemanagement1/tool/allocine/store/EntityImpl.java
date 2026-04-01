package a.entity.gus06.sys.filemanagement1.tool.allocine.store;

import a.framework.*;
import java.util.Properties;
import java.io.File;
import java.util.Map;
import java.net.URL;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200922";}
	
	public static final String KEY_CODE = "code";
	public static final String KEY_HREF = "poster";

	
	private Service urlToImage;
	private Service writeImage;
	private Service writeProp;

	public EntityImpl() throws Exception
	{
		urlToImage = Outside.service(this,"gus06.convert.urltoimage");
		writeImage = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.poster.write.image");
		writeProp = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.prop.write.map");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map movieMap = (Map) o[1];
		
		String code = (String) movieMap.get(KEY_CODE);
		if(code==null || code.equals("")) throw new Exception("Allocine Code not found inside map");
		
		writeProp.p(new Object[]{engine,code,movieMap});
		
		String href = (String) movieMap.get(KEY_HREF);
		if(href!=null && !href.equals("")) writeImage(engine,code,href);
	}
	
	
	private void writeImage(Object engine, String code, String href)
	{
		try
		{
			Object posterImage = urlToImage.t(new URL(href));
			writeImage.p(new Object[]{engine,code,posterImage});
		}
		catch(Exception e)
		{Outside.err(this,"writeImage(Object,String,String)",e);}
	}
}
