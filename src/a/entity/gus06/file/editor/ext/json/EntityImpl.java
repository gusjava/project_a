package a.entity.gus06.file.editor.ext.json;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, I, P, G, V {

	public String creationDate() {return "20150117";}

	
	private Service tab;
	private Service textGui;
	private Service dataGui;
	
	private File file;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		textGui = Outside.service(this,"*gus06.file.editor.ext.txt");
		dataGui = Outside.service(this,"*gus06.file.editor.ext.json.dataviewer");
		
		tab.v("Src",textGui.i());
		tab.v("Data",dataGui.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		textGui.p(obj);
		dataGui.p(obj);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{textGui.v(key, obj);}
}