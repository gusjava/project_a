package a.entity.gus06.sys.filetool.ext.scripttesting1.holder;

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

	public String creationDate() {return "20190825";}
	
	
	private Service findRoot;
	private Service listChooser;
	private Service viewer;
	private Service titleLabel;
	
	private Service getScriptName;
	private Service buildListing;
	private Service handleCreate;
	private Service handleRemove;
	private Service handleRename;
	private Service handleDuplicate;
	
	private JSplitPane split;
	
	private Map map;
	private File root;
	private File mappingFile;
	private String scriptName;
	
	private Map filesMap;
	private List keys;
	


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		viewer = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.gui.viewer");
		titleLabel = Outside.service(this,"*gus06.swing.label.hold.title");
		
		getScriptName = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.getscriptname");
		buildListing = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.buildlisting");
		handleCreate = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.create");
		handleRemove = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.remove");
		handleRename = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.rename");
		handleDuplicate = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.handle.duplicate");
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add((JComponent) titleLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) listChooser.i());
		split.setRightComponent(panel);
		
		listChooser.addActionListener(this);
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
		else if(s.equals("typed_F1()")) create();
		else if(s.equals("typed_F2()")) rename();
		else if(s.equals("typed_F3()")) duplicate();
		else if(s.equals("typed_F5()")) refresh();
	}
	
	
	
	

	
	private void selectionChanged()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			viewFile(key);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private void remove()
	{
		try
		{
			String key = (String) listChooser.g();
			if(key==null) return;
			
			boolean done = handleRemove.f(new Object[]{root,scriptName,key});
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
			String newKey = (String) handleCreate.t(new Object[]{root,scriptName,query});
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
			
			String newKey = (String) handleRename.t(new Object[]{root,scriptName,key});
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




	private void refresh()
	{
		try
		{
			root = (File) findRoot.t(map);
			scriptName = (String) getScriptName.t(map);
			
			reload();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	
	
	private void viewFile(String key) throws Exception
	{
		titleLabel.p(key);
		File file = (File) filesMap.get(key);
		viewer.p(file);
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
}