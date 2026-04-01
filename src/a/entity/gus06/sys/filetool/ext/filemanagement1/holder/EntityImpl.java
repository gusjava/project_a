package a.entity.gus06.sys.filetool.ext.filemanagement1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.Color;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191030";}
	
	public static final String KEY_FILTER_LATE = "filter.late";
	public static final String KEY_COLOR_LATE = "color.late";
	public static final String KEY_SCAN_PROP_LATE = "scan.prop.mode";
	public static final String KEY_SCAN_PREVIEW_LATE = "scan.preview.mode";
	public static final String KEY_SCAN_ONCOMPLETE_WARN = "scan.oncomplete.warn";
	public static final String KEY_SCAN_FILE_LIMITSIZE = "scan.file.limitsize";
	
	private Service mainGui;
	private Service findRoot;
	private Service buildFilterPastNbDays;
	private Service buildColor;
	
	private Map map;
	private File root;
	
	private F filterLate;
	private Color colorLate;
	private String scanPropMode;
	private String scanPreviewMode;
	private String scanOncompleteWarn;
	private Long scanFileLimitsize;
	

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		mainGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.maingui");
		buildFilterPastNbDays = Outside.service(this,"gus06.filter.date.build.before.now.remove.days");
		buildColor = Outside.service(this,"gus06.convert.stringtocolor");
	}
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		if(root==null || !root.isDirectory()) return;
		
		colorLate = buildColor(KEY_COLOR_LATE);
		filterLate = buildFilterLate(KEY_FILTER_LATE);
		scanPropMode = get(KEY_SCAN_PROP_LATE);
		scanPreviewMode = get(KEY_SCAN_PREVIEW_LATE);
		scanOncompleteWarn = get(KEY_SCAN_ONCOMPLETE_WARN);
		scanFileLimitsize = toLong(get(KEY_SCAN_FILE_LIMITSIZE));
		
		mainGui.p(root);
		mainGui.v(KEY_FILTER_LATE,filterLate);
		mainGui.v(KEY_COLOR_LATE,colorLate);
		mainGui.v(KEY_SCAN_PROP_LATE,scanPropMode);
		mainGui.v(KEY_SCAN_PREVIEW_LATE,scanPreviewMode);
		mainGui.v(KEY_SCAN_ONCOMPLETE_WARN,scanOncompleteWarn);
		mainGui.v(KEY_SCAN_FILE_LIMITSIZE,scanFileLimitsize);
	}
	
	
	private F buildFilterLate(String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (F) buildFilterPastNbDays.t(toInt(map.get(key)));
	}
	
	private Color buildColor(String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (Color) buildColor.t(map.get(key));
	}
	
	private String get(String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private Integer toInt(Object obj)
	{return obj!=null ? Integer.valueOf(""+obj) : null;}
	
	private Long toLong(Object obj)
	{return obj!=null ? Long.valueOf(""+obj) : null;}
}