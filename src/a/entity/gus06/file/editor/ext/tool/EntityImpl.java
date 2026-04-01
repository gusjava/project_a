package a.entity.gus06.file.editor.ext.tool;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20141229";}
	
	public static final String PATH_THIS = "path.this";
	public static final String PATH_PARENT = "path.parent";
	public static final String FULLDISPLAY = "fulldisplay";


	private Service readFile;
	private Service writeFile;
	private Service tabHolder;
	private Service shift;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	
	private JComponent tabComp;
	
	private File file;
	private Map map;
	


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.properties");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		shift = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		
		gui1 = Outside.service(this,"*gus06.sys.filetool.main.gui");
		gui2 = Outside.service(this,"*gus06.map.string.editor1");
		gui3 = Outside.service(this,"*gus06.sys.filetool.main.settingsgui");
		
		map = (Map) Outside.resource(this,"supportmap");
		addActionListener();
		
		gui2.p(map);
		
		tabComp = (JComponent) tabHolder.i();
		tabHolder.v("Tool",gui1.i());
		tabHolder.v("Prop",gui2.i());
	}
	
	
	
	public Object i() throws Exception
	{return shift.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null) resetMap();
		else updateMap();
	}
	
	
	
	private void resetMap() throws Exception
	{
		removeActionListener();
		map.clear();
		addActionListener();
		
		gui1.p(null);
		initTab();
	}
	
	
	private void updateMap() throws Exception
	{
		Map m = readFile();
		if(m==null) m = new HashMap();
		
		m.put(PATH_THIS,file.getAbsolutePath());
		m.put(PATH_PARENT,file.getParentFile().getAbsolutePath());
		
		removeActionListener();
		map.clear();
		map.putAll(m);
		addActionListener();
		
		gui1.p(map);
		gui3.p(map);
		
		if(isFullDisplay()) initFullDisplay();
		else initTab();
	}
	
	
	
	
	private void initTab() throws Exception
	{
		tabComp.removeAll();
		tabHolder.v("Tool",gui1.i());
		tabHolder.v("Prop",gui2.i());
		tabHolder.v("Settings",gui3.i());
		shift.p(tabComp);
	}
	
	private void initFullDisplay() throws Exception
	{
		shift.p(gui1.i());
	}
	
	
	
	private void addActionListener() throws Exception
	{((S) map).addActionListener(this);}
	
	private void removeActionListener() throws Exception
	{((S) map).removeActionListener(this);}
	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{if(file!=null) writeFile();}
	
	
	
	private Map readFile() throws Exception
	{return (Map) readFile.t(file);}

	
	
	private void writeFile()
	{
		try
		{writeFile.p(new Object[]{file,map});}
		catch(Exception e)
		{Outside.err(this,"writeFile()",e);}
	}
	
	
	private boolean isFullDisplay()
	{
		if(!map.containsKey(FULLDISPLAY)) return false;
		return map.get(FULLDISPLAY).equals("true");
	}
}