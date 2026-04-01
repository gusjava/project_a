package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.summary.preview.onclicked2.v2;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210306";}
	
	public static final String KEY_MD5 = "md5";


	private Service browseAllocine;
	
	public EntityImpl() throws Exception
	{
		browseAllocine = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.browse.md5");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		Map child = (Map) o[2];
		
		String md5 = (String) get(child,KEY_MD5);
		browseAllocine.p(new Object[]{engine,md5});
	}
	
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}