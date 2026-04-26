package a.entity.gus06.sys.filetool.perform.file.shift.fulldisplay;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200410";}


	private Service loadProp;
	private Service saveProp;
	private Service perform;

	public EntityImpl() throws Exception
	{
		loadProp = Outside.service(this,"gus.x.file.prop.read");
		saveProp = Outside.service(this,"gus06.file.write.properties");
		perform = Outside.service(this,"gus06.sys.filetool.perform.map.shift.fulldisplay");
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Map prop = (Map) loadProp.t(file);
		perform.p(prop);
		saveProp.p(new Object[]{file,prop});
	}
}
