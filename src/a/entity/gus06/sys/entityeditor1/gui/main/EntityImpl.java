package a.entity.gus06.sys.entityeditor1.gui.main;

import a.framework.*;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20250925";}
	
	private Service tab;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	
	private Thread t;
	private Object engine;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.sys.entityeditor1.gui.gui1");
		gui2 = Outside.service(this, "*gus.sys.entityeditor1.gui.gui2");
		gui3 = Outside.service(this, "*gus.sys.entityeditor1.gui.gui3");
		
		tab.v("ENTITY#Entities",gui1.i());
		tab.v("JAVA_import#Imports",gui2.i());
		tab.v("FILE_jar#JARs",gui3.i());
	}
	
	public Object i() throws Exception
	{return tab.i();}
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
		gui1.p(obj);
		gui2.p(obj);
		gui3.p(obj);
		
		if(t!=null && t.isAlive()) t.join();
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	public void run()
	{
		try{((E) engine).e();}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}