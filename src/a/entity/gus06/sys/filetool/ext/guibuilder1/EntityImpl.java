package a.entity.gus06.sys.filetool.ext.guibuilder1;

import a.framework.*;
import java.util.Map;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161212";}
	
	public static final String KEY_SRC = "script.src";
	public static final String KEY_PATH = "script.path";
	
	
	
	private Service findRoot;
	private Service buildG;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		buildG = Outside.service(this,"gus06.sys.script1.build2.g");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Object src = findSrc(map);
		if(src==null) return new JPanel();
		
		G g = buildG(src,map);
		if(g==null) throw new Exception("Script execution returned null");
		
		return comp(g.g());
	}
	
	
	private G buildG(Object src, Map data) throws Exception
	{return (G) buildG.t(new Object[]{src,data});}
	
	
	
	private Object findSrc(Map map) throws Exception
	{
		String src = get(map,KEY_SRC);
		if(src!=null) return src;
		
		String path = get(map,KEY_PATH);
		if(path!=null)
		{
			File f = new File(path);
			if(f.isFile()) return f;
		}
		
		File root = (File) findRoot.t(map);
		File f = new File(root,"script.gus");
		if(f.isFile()) return f;
		
		return null;
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	private JComponent comp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I) obj).i();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
