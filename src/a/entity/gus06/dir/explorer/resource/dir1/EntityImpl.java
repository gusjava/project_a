package a.entity.gus06.dir.explorer.resource.dir1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.io.File;


public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20140902";}



	private Service explorer_resources;
	private Service gridHolder;
	
	private Service viewer_ling;
	private Service viewer_icon;
	private Service viewer_prop;
	private Service viewer_jar;
	private Service viewer_dll;
	private Service viewer_store;

	private File dir;
	
	
	private JTabbedPane tab;
	


	public EntityImpl() throws Exception
	{
		explorer_resources = Outside.service(this,"*gus06.dir.explorer.simple");
		gridHolder = Outside.service(this,"*gus06.swing.panel.holder.grid1");
		
		viewer_ling = Outside.service(this,"*gus06.dir.explorer.resource.ling");
		viewer_icon = Outside.service(this,"*gus06.dir.explorer.resource.icon");
		viewer_prop = Outside.service(this,"*gus06.dir.explorer.resource.prop");
		viewer_jar = Outside.service(this,"*gus06.dir.explorer.resource.jar");
		viewer_dll = Outside.service(this,"*gus06.dir.explorer.resource.dll");
		viewer_store = Outside.service(this,"*gus06.dir.explorer.resource.store");
		
		gridHolder.v("Icon viewer",viewer_icon.i());
		gridHolder.v("Jar viewer",viewer_jar.i());
		gridHolder.v("Dll viewer",viewer_dll.i());
		
		tab = new JTabbedPane();
		
		tab.addTab("Explorer",(JComponent) explorer_resources.i());
		tab.addTab("Prop editor",(JComponent) viewer_prop.i());
		tab.addTab("Ling editor",(JComponent) viewer_ling.i());
		tab.addTab("Store viewer",(JComponent) viewer_store.i());
		tab.addTab("Icon-Jar-Dll",(JComponent) gridHolder.i());
		
	}
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		
		if(dir==null) resetGui();
		else updateGui();
	}
	
	
	
	private void resetGui() throws Exception
	{
		explorer_resources.p(null);
		viewer_store.p(null);
		viewer_ling.p(null);
		viewer_icon.p(null);
		viewer_prop.p(null);
		viewer_jar.p(null);
		viewer_dll.p(null);
	}
	
	
	
	private void updateGui() throws Exception
	{
		File iconDir = new File(dir,"icon");
		File lingDir = new File(dir,"ling");
		File jarDir = new File(dir,"jar");
		File dllDir = new File(dir,"dll");
		File storeDir = new File(dir,"store");
		
		explorer_resources.p(dir);
		viewer_prop.p(dir);
		viewer_ling.p(lingDir);
		viewer_icon.p(iconDir);
		viewer_jar.p(jarDir);
		viewer_dll.p(dllDir);
		viewer_store.p(storeDir);
	}
}
