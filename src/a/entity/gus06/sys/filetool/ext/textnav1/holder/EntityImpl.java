package a.entity.gus06.sys.filetool.ext.textnav1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20220423";}
	
	public static final String KEY_SCRIPT_SRC = "script.src";
	public static final String PATH_SCRIPTFILE = "path.scriptfile";
	public static final String PATH_DATAFILE = "path.datafile";
	public static final String KEY_ICONID = "iconid";


	private Service findRoot;
	private Service buildT;
	private Service readFile;
	private Service editor;
	private Service findIcon;
	
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		buildT = Outside.service(this,"gus06.sys.script1.build2.t");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		editor = Outside.service(this,"*gus06.sys.filetool.ext.textnav1.holder.gui1");
		findIcon = Outside.service(this,"gus06.icon.provider");
	}
	
	public Object i() throws Exception
	{return editor.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		T trans = findTrans();
		Icon icon = findIcon();
		File dataFile = findDataFile();
		
		if(trans!=null) editor.v("trans", trans);
		if(icon!=null) editor.v("icon", icon);
		if(dataFile!=null) editor.p(dataFile);
	}
	
	
	private T findTrans() throws Exception
	{
		Object src = findSrc();
		if(src==null) return null;
		return (T) buildT.t(new Object[]{src, map});
	}
	
	
	private Object findSrc() throws Exception
	{
		String src = get(map,KEY_SCRIPT_SRC);
		if(src!=null) return src;
		
		String path = get(map, PATH_SCRIPTFILE);
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
	
	
	private File findDataFile() throws Exception
	{
		String path = get(map, PATH_DATAFILE);
		if(path==null) return null;
		
		File f = new File(path);
		if(!f.exists()) f.createNewFile();
		if(f.isFile()) return f;
		
		return null;
	}
	
	
	private Icon findIcon() throws Exception
	{
		String iconId = get(map, KEY_ICONID);
		if(iconId==null) return null;
		
		return (Icon) findIcon.t(iconId);
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}