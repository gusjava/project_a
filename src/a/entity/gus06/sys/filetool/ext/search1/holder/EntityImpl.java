package a.entity.gus06.sys.filetool.ext.search1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160915";}
	
	public final static String KEY_ROOTS = "roots";
	public final static String KEY_WIDTH_MAP = "width_map";
	public final static String KEY_ORIENTATION = "orientation";
	public final static String KEY_INPUT_PERSIST = "input_persist";
	public final static String KEY_FILE_FILTER = "file_filter";
	public final static String KEY_TERMS_BUILDER = "terms_builder";
	public final static String KEY_ROOTS_BUILDER = "roots_builder";
	public final static String KEY_ROOTS_BUILDER_MODE = "roots_builder_mode";
	
	public static final String KEY_WIDTH = "width.";
	public static final String KEY_MODE = "mode.";
	
	
	private Service findRoots;
	private Service mainGui;
	private Service subMap;
	private Service access;
	private Service wrapKey;
	private Service buildT;
	private Service buildF;
	private Service buildG;
	
	private Map map;
	private Map widthMap;
	private Object roots;
	private String rootsBuilderMode;
	private String orientation;
	private Object inputPersist;
	private T termsBuilder;
	private F fileFilter;
	

	public EntityImpl() throws Exception
	{
		findRoots = Outside.service(this,"gus06.sys.filetool.findroots");
		mainGui = Outside.service(this,"*gus06.sys.dirsearch1.gui.maingui1");
		subMap = Outside.service(this,"gus06.map.string.submap");
		access = Outside.service(this,"gus06.file.properties.access.submap");
		wrapKey = Outside.service(this,"gus06.map.wrap.key");
		buildT = Outside.service(this,"gus06.sys.script1.build2.t");
		buildF = Outside.service(this,"gus06.sys.script1.build2.f");
		buildG = Outside.service(this,"gus06.sys.script1.build2.g");
	}
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		roots = buildRoots();
		widthMap = (Map) subMap.t(new Object[]{map,KEY_WIDTH});
		inputPersist = wrapKey.t(new Object[]{map,KEY_INPUT_PERSIST});
		orientation = get0(KEY_ORIENTATION);
		rootsBuilderMode = get0(KEY_ROOTS_BUILDER_MODE);
		termsBuilder = buildTermsBuilder();
		fileFilter = buildFileFilter();
		
		mainGui.v("roots",roots);
		mainGui.v("widthMap",widthMap);
		mainGui.v("orientation",orientation);
		mainGui.v("inputPersist",inputPersist);
		mainGui.v("termsBuilder",termsBuilder);
		mainGui.v("fileFilter",fileFilter);
	}
	
	
	
	
	private Object buildRoots() throws Exception
	{
		String src = (String) get0(KEY_ROOTS_BUILDER);
		if(src!=null)
		{
			G g = (G) buildG.t(new Object[]{src,map});
			if(rootsBuilderMode!=null)
			{
				if(rootsBuilderMode.equals("static")) return g.g();
				if(rootsBuilderMode.equals("dynamic")) return g;
			}
			return g;
		}
		return findRoots.t(map);
	}
	
	
	
	
	private T buildTermsBuilder() throws Exception
	{
		String src = (String) get0(KEY_TERMS_BUILDER);
		if(src==null) return null;
		return (T) buildT.t(new Object[]{src,map});
	}
	
	
	
	private F buildFileFilter() throws Exception
	{
		String src = (String) get0(KEY_FILE_FILTER);
		if(src==null) return null;
		return (F) buildF.t(new Object[]{src,map});
	}
	
	
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
