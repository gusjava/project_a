package a.entity.gus06.sys.filetool.ext.splitpane.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200313";}

	public static final String KEY_PATH1 = "path1";
	public static final String KEY_PATH2 = "path2";
	
	public static final String KEY_HISTORY1 = "history1";
	public static final String KEY_HISTORY2 = "history2";
	
	public static final String KEY_ORIENTATION = "orientation";
	

	private Service editor1;
	private Service editor2;
	private Service onKey;
	private Service clipboard;
	private Service listToString;
	private Service stringToList;
	
	private Map map;
	private JSplitPane split;
	
	private Object fileLabel1;
	private Object fileLabel2;
	
	private JComponent label1;
	private JComponent label2;
	
	private ActionListener editor1Listener;
	private ActionListener editor2Listener;
	
	private ActionListener fileLabel1Listener;
	private ActionListener fileLabel2Listener;




	public EntityImpl() throws Exception
	{
		editor1 = Outside.service(this,"*gus06.file.editor.main2-1");
		editor2 = Outside.service(this,"*gus06.file.editor.main2-2");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		clipboard = Outside.service(this,"gus06.clipboard.access.listfiles");
		listToString = Outside.service(this,"gus06.tostring.list.join.semicolon");
		stringToList = Outside.service(this,"gus06.string.split.delim.semicolon.list");
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) editor1.i());
		split.setRightComponent((JComponent) editor2.i());
		
		fileLabel1 = editor1.r("fileLabel");
		fileLabel2 = editor2.r("fileLabel");
		
		label1 = (JComponent) ((I) fileLabel1).i();
		label2 = (JComponent) ((I) fileLabel2).i();
		
		onKey.p(new Object[]{label1,"ctrl v",(E) this::copyToLabel1});
		onKey.p(new Object[]{label2,"ctrl v",(E) this::copyToLabel2});
		
		onKey.p(new Object[]{label1,"ctrl h",(E) this::shiftOrientation});
		onKey.p(new Object[]{label2,"ctrl h",(E) this::shiftOrientation});
		
		onKey.p(new Object[]{label1,"ctrl p",(E) this::reloadPrevious1});
		onKey.p(new Object[]{label2,"ctrl p",(E) this::reloadPrevious2});
		
		
		editor1Listener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{receive1();}
		};
		
		editor2Listener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{receive2();}
		};
		
		fileLabel1Listener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("historyChanged()")) history1Changed();
			}
		};
		
		fileLabel2Listener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("historyChanged()")) history2Changed();
			}
		};
		
		addListeners();
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		File file1 = getFile(KEY_PATH1);
		File file2 = getFile(KEY_PATH2);
		
		List history1 = getHistory(KEY_HISTORY1);
		List history2 = getHistory(KEY_HISTORY2);
		
		removeListeners();
		
		editor1.p(file1);
		editor2.p(file2);
		
		((V) fileLabel1).v("history", history1);
		((V) fileLabel2).v("history", history2);
		
		addListeners();
		
		if(map.containsKey(KEY_ORIENTATION))
		refreshOrientation();
		
		split.setDividerSize(3);
		split.setDividerLocation(400);
	}
	
	
	
	private void addListeners() throws Exception
	{
		editor1.addActionListener(editor1Listener);
		editor2.addActionListener(editor2Listener);
		
		((S)fileLabel1).addActionListener(fileLabel1Listener);
		((S)fileLabel2).addActionListener(fileLabel2Listener);
	}
	
	private void removeListeners() throws Exception
	{
		editor1.removeActionListener(editor1Listener);
		editor2.removeActionListener(editor2Listener);
		
		((S)fileLabel1).removeActionListener(fileLabel1Listener);
		((S)fileLabel2).removeActionListener(fileLabel2Listener);
	}
	
	
	
	private void refreshOrientation()
	{
		String orientation = (String) map.get(KEY_ORIENTATION);
		if(orientation.equals("v"))
			split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		else if(orientation.equals("h"))
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
	}
	
	
	
	
	private File getFile(String key)
	{
		if(!map.containsKey(key)) return null;
		String path = (String) map.get(key);
		return new File(path);
	}
	private List getHistory(String key) throws Exception
	{
		if(!map.containsKey(key)) return new ArrayList();
		String str = (String) map.get(key);
		return (List) stringToList.t(str);
	}
	
	
	
	private void saveFile(String key, File f)
	{
		map.put(key,f.getAbsolutePath());
	}
	
	private void saveHistory(String key, List history) throws Exception
	{
		String str = (String) listToString.t(history);
		map.put(key, str);
	}
	
	
	
	
	
	private void receive1()
	{
		try
		{
			File f1 = (File) editor1.g();
			File f2_ = getFile(KEY_PATH2);
			
			if(f1.equals(f2_))
			{
				File f1_ = getFile(KEY_PATH1);
				editor2.p(f1_);
				saveFile(KEY_PATH2,f1_);
			}
			saveFile(KEY_PATH1,f1);
		}
		catch(Exception e)
		{Outside.err(this,"receive1()",e);}
	}
	
	
	private void receive2()
	{
		try
		{
			File f2 = (File) editor2.g();
			File f1_ = getFile(KEY_PATH1);
			
			if(f2.equals(f1_))
			{
				File f2_ = getFile(KEY_PATH2);
				editor1.p(f2_);
				saveFile(KEY_PATH1,f2_);
			}
			saveFile(KEY_PATH2,f2);
		}
		catch(Exception e)
		{Outside.err(this,"receive2()",e);}
	}
	
	
	private void copyToLabel1()
	{
		try
		{
			List list = (List) clipboard.g();
			if(list==null || list.size()!=2) return;
			
			File f1 = (File) list.get(0);
			File f2 = (File) list.get(1);
			
			editor1.p(f1);
			editor2.p(f2);
			
			saveFile(KEY_PATH1,f1);
			saveFile(KEY_PATH2,f2);
		}
		catch(Exception e){Outside.err(this,"copyToLabel1()",e);}
	}
	
	private void copyToLabel2()
	{
		try
		{
			List list = (List) clipboard.g();
			if(list==null || list.size()!=2) return;
			
			File f2 = (File) list.get(0);
			File f1 = (File) list.get(1);
			
			editor1.p(f1);
			editor2.p(f2);
			
			saveFile(KEY_PATH1,f1);
			saveFile(KEY_PATH2,f2);
		}
		catch(Exception e){Outside.err(this,"copyToLabel2()",e);}
	}
	
	
	
	private void shiftOrientation()
	{
		if(map==null) return;
		String orientation = get(KEY_ORIENTATION,"h");
		map.put(KEY_ORIENTATION,orientation.equals("h")?"v":"h");
		refreshOrientation();
	}
	
	
	
	private void reloadPrevious1()
	{
		try
		{
			List history = (List) ((R) fileLabel1).r("history");
			if(history.size()<2) return;
			
			File f = new File((String) history.get(1));
			editor1.p(f);
		}
		catch(Exception e)
		{Outside.err(this,"reloadPrevious1()",e);}
	}
	
	
	
	private void reloadPrevious2()
	{
		try
		{
			List history = (List) ((R) fileLabel2).r("history");
			if(history.size()<2) return;
			
			File f = new File((String) history.get(1));
			editor2.p(f);
		}
		catch(Exception e)
		{Outside.err(this,"reloadPrevious2()",e);}
	}

	
	
	
	private void history1Changed()
	{
		try
		{
			List history = (List) ((R) fileLabel1).r("history");
			saveHistory(KEY_HISTORY1, history);
		}
		catch(Exception e)
		{Outside.err(this,"history1Changed()",e);}
	}
	
	private void history2Changed()
	{
		try
		{
			List history = (List) ((R) fileLabel2).r("history");
			saveHistory(KEY_HISTORY2, history);
		}
		catch(Exception e)
		{Outside.err(this,"history2Changed()",e);}
	}


	
	
	private String get(String key, String defaultValue)
	{
		if(map==null || !map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
}