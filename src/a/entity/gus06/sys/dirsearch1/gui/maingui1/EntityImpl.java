package a.entity.gus06.sys.dirsearch1.gui.maingui1;

import a.framework.*;

public class EntityImpl implements Entity, I, V, E {

	public String creationDate() {return "20200123";}


	private Service tab;
	private Service searchGui;
	private Service modeGui;
	private Service modeManager;


	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		searchGui = Outside.service(this,"*gus06.sys.dirsearch1.gui.search");
		modeGui = Outside.service(this,"*gus06.sys.dirsearch1.gui.mode");
		modeManager = Outside.service(this,"*gus06.sys.dirsearch1.mode.manager");
		
		searchGui.v("modeManager",modeManager);
		modeGui.v("modeManager",modeManager);
		
		tab.v("File content",searchGui.i());
		tab.v("Modes",modeGui.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("roots"))
		{searchGui.v("roots",obj);return;}
		
		if(key.equals("orientation"))
		{searchGui.v("orientation",obj);return;}
		
		if(key.equals("widthMap"))
		{searchGui.v("widthMap",obj);return;}
		
		if(key.equals("inputPersist"))
		{searchGui.v("inputPersist",obj);return;}
		
		if(key.equals("input"))
		{searchGui.v("input",obj);return;}
		
		if(key.equals("modeManager"))
		{searchGui.v("modeManager",obj);return;}
		
		if(key.equals("termsBuilder"))
		{searchGui.v("termsBuilder",obj);return;}
		
		if(key.equals("fileFilter"))
		{searchGui.v("fileFilter",obj);return;}
		
		
		throw new Exception("Unknown key: "+key);
	}



	public void e() throws Exception
	{
		searchGui.e();
	}
}
