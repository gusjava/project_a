package a.entity.gus06.appli.gusexplorer.init.rootchanger;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity {

	public String creationDate() {return "20151003";}


	private Service performChangeRoot;
	private Service performAddNewTab;
	private Service manager;


	public EntityImpl() throws Exception
	{
		performChangeRoot = Outside.service(this,"gus06.swing.tree.perform.file.changeroot");
		performAddNewTab = Outside.service(this,"gus06.swing.tree.perform.file.addnewtab");
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		
		performChangeRoot.v("rootChanger",new P(){
			public void p(Object obj) throws Exception {changeRoot(obj);}
		});
		performAddNewTab.v("tabAdder",new P(){
			public void p(Object obj) throws Exception {addTab(obj);}
		});
	}
	
	
	
	private void changeRoot(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length!=2) throw new Exception("Wrong number: "+f.length);
		manager.v("modify",f);
	}
	
	private void addTab(Object obj) throws Exception
	{
		manager.p(obj);
	}
}
