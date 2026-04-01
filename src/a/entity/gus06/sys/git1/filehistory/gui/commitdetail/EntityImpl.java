package a.entity.gus06.sys.git1.filehistory.gui.commitdetail;

import a.framework.*;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20201129";}


	private Service tab;
	private Service guiDiff;
	private Service guiSrc;
	private Service guiCommit;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		guiDiff = Outside.service(this,"*gus06.sys.git1.filehistory.gui.commitdetail.diff");
		guiSrc = Outside.service(this,"*gus06.sys.git1.filehistory.gui.commitdetail.src");
		guiCommit = Outside.service(this,"*gus06.sys.git1.filehistory.gui.commitdetail.commit");
		
		tab.v("GIT_state_d#Diff",guiDiff.i());
		tab.v("GIT_src#Source",guiSrc.i());
		tab.v("GIT_commits#Commit",guiCommit.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		guiDiff.p(obj);
		guiSrc.p(obj);
		guiCommit.p(obj);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("selectionHandler"))
		{
			guiDiff.v("selectionHandler",obj);
			guiSrc.v("selectionHandler",obj);
			return;
		}
		
		throw new Exception("Unknown key: "+key);
	}
}