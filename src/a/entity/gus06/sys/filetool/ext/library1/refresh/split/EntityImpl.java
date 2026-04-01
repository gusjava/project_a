package a.entity.gus06.sys.filetool.ext.library1.refresh.split;

import a.framework.*;
import java.util.Map;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200311";}

	public static final String DIVIDERLOC = "dividerloc";
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JSplitPane split = (JSplitPane) o[0];
		Map map = (Map) o[1];
		
		if(!map.containsKey(DIVIDERLOC)) return;
		
		String dividerloc = (String) map.get(DIVIDERLOC);
		int loc = Integer.parseInt(dividerloc);
		split.setDividerLocation(loc);
	}
}
