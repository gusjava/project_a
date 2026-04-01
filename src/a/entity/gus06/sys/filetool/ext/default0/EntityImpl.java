package a.entity.gus06.sys.filetool.ext.default0;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150325";}
	
	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		return new JPanel();
	}
}
