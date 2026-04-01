package a.entity.gus06.sys.filetool.ext.javatoolbox1.holder;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20161107";}
	
	
	private Service findRoot;
	private Service tab;
	
	private Service gui_charAnalyzer;
	private Service gui_colorPicker;
	private Service gui_charset;
	private Service gui_font;
	private Service gui_locale;
	private Service gui_javaTypes;
	private Service gui_codePoints;
	private Service gui_jdbcDrivers;
	
	private JPanel panel;
	
	private Map map;
	private File root;
	

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		gui_charAnalyzer = Outside.service(this,"*gus06.sys.charanalyzer1.gui.maingui");
		gui_colorPicker = Outside.service(this,"*gus06.sys.colorpicker1.gui.panel");
		gui_charset = Outside.service(this,"*gus06.charset.gui.displaygui");
		gui_font = Outside.service(this,"*gus06.font.availablefonts.gui");
		gui_locale = Outside.service(this,"*gus06.java.tool.locale.gui1");
		gui_javaTypes = Outside.service(this,"*gus06.java.tool.javatypes.gui1");
		gui_codePoints = Outside.service(this,"*gus06.appli.gusjavatoolbox.gui.codepoint");
		gui_jdbcDrivers = Outside.service(this,"*gus06.jdbc.drivers.gui");
		
		tab.v("Char analyzer",gui_charAnalyzer.i());
		tab.v("Color picker",gui_colorPicker.i());
		tab.v("Charset",gui_charset.i());
		tab.v("Fonts",gui_font.i());
		tab.v("Locales",gui_locale.i());
		tab.v("Java types",gui_javaTypes.i());
		tab.v("Code points",gui_codePoints.i());
		tab.v("JDBC drivers",gui_jdbcDrivers.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
	}
	
	private String get(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) throw new Exception("Key not found inside tool: "+key);
		return (String) map.get(key);
	}
}
