package a.entity.gus06.dir.explorer.simple.view;

import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import java.util.List;
import javax.swing.JSplitPane;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, P, R {

	public String creationDate() {return "20221002";}


	private Service editor;
	private Service editor1;
	private Service editor2;
	private Service shiftPanel;
	private Service splitCust;
	
	private JSplitPane split;


	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.main-1");
		editor1 = Outside.service(this,"*gus06.file.editor.main-2");
		editor2 = Outside.service(this,"*gus06.file.editor.main-3");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
        
		split = new JSplitPane();
		split.setLeftComponent((JComponent) editor1.i());
		split.setRightComponent((JComponent) editor2.i());
		splitCust.p(split);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}


	public void p(Object obj) throws Exception
	{
		if(obj==null) reset();
		else if(obj instanceof File) handleFile((File) obj);
		else if(obj instanceof List) handleList((List) obj);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("editor")) return editor;
		if(key.equals("editor1")) return editor1;
		if(key.equals("editor2")) return editor2;
		
		if(key.equals("keys")) return new String[]{"editor","editor1","editor2"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void reset() throws Exception
	{
		editFile(editor,null);
		editFile(editor1,null);
		editFile(editor2,null);
		shiftPanel.p(null);
	}
	
	private void handleFile(File file) throws Exception
	{
		if(file.isDirectory()) return;
		
		editFile(editor, file);
		shiftPanel.p(editor.i());
	}
	
	private void handleList(List list) throws Exception
	{
		List files = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			File f = (File) list.get(i);
			if(f.isFile()) files.add(f);
		}
		
		if(files.isEmpty()) return;
		if(files.size()==1)
		{
			File file = (File) files.get(0);
			handleFile(file);
			return;
		}
		File file1 = (File) files.get(0);
		File file2 = (File) files.get(1);
		
		editFile(editor1,file1);
		editFile(editor2,file2);
		
		shiftPanel.p(split);
	}
	
	private void editFile(P editor, File file) throws Exception
	{
		try{editor.p(file);}
		catch(Exception e)
		{
			String message = "Failed to edit file: "+file;
			throw new Exception(message, e);
		}
	}
}