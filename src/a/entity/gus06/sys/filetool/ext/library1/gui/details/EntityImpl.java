package a.entity.gus06.sys.filetool.ext.library1.gui.details;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221018";}

	private Service editor;
	private Service editor1;
	private Service editor2;
	private Service shiftPanel;
	private Service splitCust;
	private Service initCtrlH;
	
	private JSplitPane split;


	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.main-1");
		editor1 = Outside.service(this,"*gus06.file.editor.main-2");
		editor2 = Outside.service(this,"*gus06.file.editor.main-3");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		initCtrlH = Outside.service(this,"gus06.swing.splitpane.init.inv.ctrl_h");
        
		split = new JSplitPane();
		split.setLeftComponent((JComponent) editor1.i());
		split.setRightComponent((JComponent) editor2.i());
		splitCust.p(split);
		
		Object fileLabel1 = editor1.r("fileLabel");
		Object fileLabel2 = editor2.r("fileLabel");
		initCtrlH.p(new Object[]{split,fileLabel1,fileLabel2});
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
		editFile(editor, file);
		shiftPanel.p(editor.i());
	}
	
	private void handleList(List list) throws Exception
	{
		List files = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			File f = (File) list.get(i);
			files.add(f);
		}
		
		if(files.isEmpty())
		{
			reset();
			return;
		}
		
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