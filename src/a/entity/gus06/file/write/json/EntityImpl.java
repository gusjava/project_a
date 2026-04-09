package a.entity.gus06.file.write.json;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251211";}


	private Service writeString;
	private Service generateJson;

	public EntityImpl() throws Exception
	{
		writeString = Outside.service(this,"gus06.file.write.string");
		generateJson = Outside.service(this,"gus.x.json.build1");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String text = generateJson(o[1]);
		
		writeString.p(new Object[]{file,text});
	}
	
	private String generateJson(Object obj) throws Exception
	{return (String) generateJson.t(obj);}
}
