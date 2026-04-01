package a.entity.gus06.sys.filemanagement1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.io.File;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20191107";}


	private Service tab;
	private Service buildEngine;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	
	private File root;
	private Object engine;
	
	
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		buildEngine = Outside.service(this,"gus06.sys.filemanagement1.build.engine");
		
		gui1 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1.files");
		gui2 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2.movies");
		gui3 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3.ebooks");
		gui4 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui4.pdfs");
		
		tab.v("CLIPBOARD_files#Files",gui1.i());
		tab.v("MOVIE#Movies",gui2.i());
		tab.v("EBOOK#Ebooks",gui3.i());
		tab.v("FILE_pdf#PDFs",gui4.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		root = (File) obj;
		if(root!=null && root.isDirectory())
			engine = buildEngine.t(root);
		else engine = null;
		
		gui1.p(engine);
		gui2.p(engine);
		gui3.p(engine);
		gui4.p(engine);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(engine==null) throw new Exception("Engine not initialized yet");
		((V)engine).v("config:"+key,obj);
	}
}