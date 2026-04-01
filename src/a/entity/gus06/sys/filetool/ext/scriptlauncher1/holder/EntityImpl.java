package a.entity.gus06.sys.filetool.ext.scriptlauncher1.holder;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.util.Collections;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20160927";}
	
	public static final String KEY_MAPPING = "path.mappingfile";
	public static final String KEY_INITSCRIPT = "init.script";
	
	private Service findRoot;
	private Service listChooser;
	private Service viewer;
	private Service titleLabel;
	private Service onKey;
	
	private Service getScriptName;
	private Service buildListing;
	private Service handleCreate;
	private Service handleRemove;
	private Service handleRename;
	private Service handleDuplicate;
	private Service handleExecute;
	private Service handleCopyName;
	private Service handleCopyFile;
	private Service handleCopyPath;
	
	private JSplitPane split;
	private JComponent listComp;
	
	private Map map;
	private File root;
	private File mappingFile;
	private String scriptName;  //correspond � "script.gus"
	
	private Map filesMap;
	private List keys;
	


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		viewer = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.gui.viewer");
		titleLabel = Outside.service(this,"*gus06.swing.label.hold.title");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		
		getScriptName = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.getscriptname");
		buildListing = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.buildlisting");
		handleCreate = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.create");
		handleRemove = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.remove");
		handleRename = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.rename");
		handleDuplicate = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.duplicate");
		handleExecute = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.execute");
		handleCopyName = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.copyname");
		handleCopyFile = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.copyfile");
		handleCopyPath = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.copypath");
		
		listChooser.v("mode","one");
		listComp = (JComponent) listChooser.r("list");
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add((JComponent) titleLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) listChooser.i());
		split.setRightComponent(panel);
		
		listChooser.addActionListener(this);
		
		onKey.p(new Object[]{listComp,"ctrl c",		(E) this::copyName});
		onKey.p(new Object[]{listComp,"ctrl shift c",	(E) this::copyFile});
		onKey.p(new Object[]{listComp,"ctrl alt c",	(E) this::copyPath});
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		refresh();
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		
		if(s.equals("selectionChanged()")) selectionChanged();
		else if(s.equals("typed_delete()")) remove();
		else if(s.equals("typed_space()")) execute();
		else if(s.equals("typed_F1()")) create();
		else if(s.equals("typed_F2()")) rename();
		else if(s.equals("typed_F3()")) duplicate();
		else if(s.equals("typed_F5()")) refresh();
	}
	
	
	
	

	
	private void selectionChanged()
	{
		try
		{
			String scriptPath = (String) listChooser.g();
			if(scriptPath==null) return;
			
			viewFile(scriptPath);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private void remove()
	{
		try
		{
			String path = (String) listChooser.g();
			if(path==null) return;
			
			boolean done = handleRemove.f(new Object[]{root, scriptName, path});
			if(!done) return;
			
			reload();
		}
		catch(Exception e)
		{Outside.err(this,"remove()",e);}
	}
	
	
	
	
	private void create()
	{
		try
		{
			String query = (String) listChooser.r("query");
			String initScript = findInitScript();
			
			String newKey = (String) handleCreate.t(new Object[]{root, scriptName, initScript, query});
			if(newKey==null) return;
			
			reload();
			select(newKey);
		}
		catch(Exception e)
		{Outside.err(this,"create()",e);}
	}
	
	
	
	private void rename()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			String newKey = (String) handleRename.t(new Object[]{root, scriptName, key});
			if(newKey==null) return;
			
			reload();
			select(newKey);
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}
	
	
	
	
	private void duplicate()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			String newKey = (String) handleDuplicate.t(new Object[]{root,key});
			if(newKey==null) return;
			
			reload();
			select(newKey);
		}
		catch(Exception e)
		{Outside.err(this,"duplicate()",e);}
	}



	private void execute()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			handleExecute.p(new Object[]{root,key,scriptName});
		}
		catch(Exception e)
		{Outside.err(this,"execute()",e);}
	}


	private void copyName()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			handleCopyName.p(new Object[]{root,key,scriptName});
		}
		catch(Exception e)
		{Outside.err(this,"copyName()",e);}
	}


	private void copyFile()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			handleCopyFile.p(new Object[]{root,key,scriptName});
		}
		catch(Exception e)
		{Outside.err(this,"copyFile()",e);}
	}


	private void copyPath()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			handleCopyPath.p(new Object[]{root,key,scriptName});
		}
		catch(Exception e)
		{Outside.err(this,"copyPath()",e);}
	}




	private void refresh()
	{
		try
		{
			root = (File) findRoot.t(map);
			mappingFile = findMappingFile();
			scriptName = (String) getScriptName.t(map);
			
			viewer.v("root", root);
			viewer.v("mappingFile", mappingFile);
			viewer.v("scriptName", scriptName);
			reload();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	
	
	private void viewFile(String scriptPath) throws Exception
	{
		titleLabel.p(scriptPath);
		File file = (File) filesMap.get(scriptPath);
		
		viewer.p(file);
		viewer.v("scriptPath", scriptPath);
	}
	
	
	private void reload() throws Exception
	{
		filesMap = (Map) buildListing.t(new Object[]{root,scriptName});
		keys = new ArrayList(filesMap.keySet());
		Collections.sort(keys);
		listChooser.p(keys);
	}
	
	
	private void select(String key) throws Exception
	{
		listChooser.v("select",key);
	}
	
	
	private File findMappingFile()
	{
		String mappingPath = get(KEY_MAPPING);
		return mappingPath!=null ? new File(mappingPath) : null;
	}
	
	private String findInitScript()
	{
		return get(KEY_INITSCRIPT);
	}
	
	private String get(String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}