package a.entity.gus06.file.video.perform.convert.towav;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260129";}
	
	public static final String PROPKEY = "script.convert.videotowav";
	
	private Map prop;
	private Service buildP;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		buildP = Outside.service(this,"gus06.sys.script1.build2.p");
	}

	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File videoFile = o[0];
		File wavFile = o[1];
		
		Map input = new HashMap();
		input.put("inputFile",videoFile);
		input.put("outputFile",wavFile);
		
		if(!prop.containsKey(PROPKEY))
		throw new Exception("Unsupported operation: converting video to wav");
		String script = (String) prop.get(PROPKEY);
		
		P p = (P) buildP.t(new Object[]{script, null});
		p.p(input);
	}
}
