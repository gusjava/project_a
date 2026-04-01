package a.entity.gus06.sys.quickreplace.holder.find;

import a.framework.*;
import java.util.Map;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}


	private Service build;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.sys.quickreplace.holder.build");
	}
	
	public synchronized Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Map data = (Map) ((R) comp).r("data");
		
		if(!data.containsKey("quickreplace"))
			data.put("quickreplace",build.t(comp));
		return data.get("quickreplace");
	}
}