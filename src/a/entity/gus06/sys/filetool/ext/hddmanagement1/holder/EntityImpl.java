package a.entity.gus06.sys.filetool.ext.hddmanagement1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.Color;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191031";}
	
	public static final String KEY_FILTER_EXCLUDE = "filter.exclude";
	public static final String KEY_FILTER_KEEP = "filter.keep";
	public static final String KEY_FILTER_LATE = "filter.late";
	public static final String KEY_COLOR_RATIO = "color.ratio";
	public static final String KEY_COLOR_LATE = "color.late";
	public static final String KEY_DESC = "desc.";
	public static final String KEY_WIDTH = "width.";
	
	private Service mainGui;
	private Service findRoot;
	private Service buildFilterOneOfThem;
	private Service buildFilterPastNbDays;
	private Service buildColor;
	private Service subMap;
	
	private Map map;
	private File root;
	
	private F filterKeep;
	private F filterExclude;
	private F filterLate;
	
	private Color colorRatio;
	private Color colorLate;
	private Map descMap;
	private Map widthMap;

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		mainGui = Outside.service(this,"*gus06.sys.hddmanagement1.gui.maingui");
		buildFilterOneOfThem = Outside.service(this,"gus06.filter.string.build.equals.oneofthem");
		buildFilterPastNbDays = Outside.service(this,"gus06.filter.date.build.before.now.remove.days");
		buildColor = Outside.service(this,"gus06.convert.stringtocolor");
		subMap = Outside.service(this,"gus06.map.string.submap");
	}
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		filterKeep = buildFilterNames(KEY_FILTER_KEEP);
		filterExclude = buildFilterNames(KEY_FILTER_EXCLUDE);
		filterLate = buildFilterLate(KEY_FILTER_LATE);
		
		colorRatio = buildColor(KEY_COLOR_RATIO);
		colorLate = buildColor(KEY_COLOR_LATE);
		descMap = (Map) subMap.t(new Object[]{map,KEY_DESC});
		widthMap = (Map) subMap.t(new Object[]{map,KEY_WIDTH});
		
		mainGui.v("filter_name",new FilterName());
		mainGui.v("filter_late",filterLate);
		mainGui.v("color_ratio",colorRatio);
		mainGui.v("color_late",colorLate);
		mainGui.v("desc_map",descMap);
		mainGui.v("width_map",widthMap);
		
		mainGui.p(root);
	}
	
	private F buildFilterNames(String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (F) buildFilterOneOfThem.t(map.get(key));
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
	
	private Integer toInt(Object obj)
	{return Integer.valueOf(""+obj);}
	
	private class FilterName implements F
	{
		public boolean f(Object obj) throws Exception
		{
			if(filterExclude!=null && filterExclude.f(obj)) return false;
			return filterKeep!=null ? filterKeep.f(obj) : true;
		}
	}
}
