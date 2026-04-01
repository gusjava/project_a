package a.entity.gus06.file.editor.ext.tree;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;
import java.util.Map;

public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20190529";}

	
	private Service holder;
	
	private Service handleF2;
	private Service handleF3;
	
	private File file;
	private Object comp;
	
	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.sys.filetool.ext.idea1.holder.gui1");
		
		handleF2 = Outside.service(this,"gus06.swing.textcomp.cust.action.f2.autocomplete");
		handleF3 = Outside.service(this,"gus06.swing.textcomp.cust.action.f3.autocomplete");
		
		comp = holder.r("comp");
		
		handleF2.p(comp);
		handleF3.p(comp);
	}
	
	
	public Object i() throws Exception
	{return holder.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("file")) return file;
		if(key.equals("keys")) return new String[]{"comp","file"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		holder.p(file);
		
		((Map) ((R)comp).r("data")).put("file",file);
	}
}