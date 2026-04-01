package a.entity.gus06.sys.filetool.main.settingsgui.maptocomp;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230216";}


	private Service mapToName;
	private Service uniqueEntity;

	public EntityImpl() throws Exception
	{
		mapToName = Outside.service(this,"gus06.sys.filetool.main.maptoname");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null) return null;
		
		String name = (String) mapToName.t(map);
		if(name==null) return null;
		
		try
		{
			T trans = (T) uniqueEntity.t(name+".settings");
			return (JComponent) trans.t(map);
		}
		catch(Exception e) {return null;}
	}
}