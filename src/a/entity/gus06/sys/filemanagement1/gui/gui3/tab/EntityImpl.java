package a.entity.gus06.sys.filemanagement1.gui.gui3.tab;

import a.framework.*;
import java.awt.BorderLayout;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EntityImpl implements Entity, ActionListener, I, P, V {

	public String creationDate() {return "20201103";}
	
	public static final String ENGINE_KEY = "dirGenerated_ebook";

	private Service getName0;
	private Service readList;
	private Service listViewer;
	private Service ebookViewer;
	private Service custSplit;
	private Service readCounts;
	private Service onF2;
	private Service modifier;

	private JPanel panel;
	private JSplitPane split;
	private JButton button;
	
	private Object engine;
	private File rootDir;
	private File countFile;
	private Map map;
	private List keys;
	
	private String fieldName;
	private String propName;
	private String selectedName;
	
	

	public EntityImpl() throws Exception
	{
		getName0 = Outside.service(this,"gus.x.file.getname0");
		readList = Outside.service(this,"gus06.file.read.string.list.autodetect");
		listViewer = Outside.service(this,"*gus06.sys.countmap1.gui.maingui");
		ebookViewer = Outside.service(this,"*gus06.sys.filemanagement1.gui.ebooks.ebooklist.viewer");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		readCounts = Outside.service(this,"gus06.file.read.string.map.tn.count.utf8");
		onF2 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f2");
		modifier = Outside.service(this,"gus06.sys.filemanagement1.tool.prop.modifier.byvalue");
		
		map = new HashMap();
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) listViewer.i());
		split.setRightComponent((JComponent) ebookViewer.i());
		
		custSplit.p(split);
		
		button = new JButton("Refresh");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		
		Object table = listViewer.r("table");
		onF2.p(new Object[]{table,(E) this::rename});
		
		listViewer.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
		refresh();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("fieldName")) {fieldName = (String) obj;return;}
		if(key.equals("propName")) {propName = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void refresh()
	{
		try
		{
			if(fieldName==null) throw new Exception("fieldName not initialized yet");
			
			if(engine==null){reset();return;}
			
			File dirGen = (File) ((R) engine).r(ENGINE_KEY);
			File countFile = new File(new File(dirGen,"counts"),fieldName+".txt");
			if(!countFile.exists()){reset();return;}
			
			rootDir = new File(dirGen,fieldName);
			if(rootDir==null){reset();return;}
			
			map = (Map) readCounts.t(countFile);
			keys = new ArrayList(map.keySet());
			Collections.sort(keys);
			
			listViewer.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void reset() throws Exception
	{
		rootDir = null;
		map = null;
		keys = null;
		listViewer.p(null);
	}



	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	
	private void selected()
	{
		try
		{
			selectedName = (String) listViewer.g();
			List md5List = loadMd5List();
			ebookViewer.p(new Object[]{engine,md5List});
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
	
	
	
	private List loadMd5List() throws Exception
	{
		if(selectedName==null) return null;
		if(fieldName==null) return null;
		if(keys==null) return null;
		
		int index = keys.indexOf(selectedName);
		File dirGen = (File) ((R) engine).r(ENGINE_KEY);
		File file = new File(new File(dirGen,fieldName),index+".txt");
		if(!file.isFile()) return null;
		
		return (List) readList.t(file);
	}
	
	
	private void rename()
	{
		try
		{
			if(propName==null) return;
			
			boolean done = modifier.f(new Object[]{engine,ENGINE_KEY,fieldName,propName,selectedName});
			if(done) refresh();
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}

}