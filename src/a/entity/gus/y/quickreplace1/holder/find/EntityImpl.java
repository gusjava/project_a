package a.entity.gus.y.quickreplace1.holder.find;

import a.framework.*;
import java.util.Map;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service build;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus.y.quickreplace1.holder.build");
	}
	
	public synchronized Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		if(!(comp instanceof R)) return null;
		
		Map data = (Map) ((R) comp).r("data");
		
		if(!data.containsKey("quickreplace"))
			data.put("quickreplace",build.t(comp));
		return data.get("quickreplace");
	}
}