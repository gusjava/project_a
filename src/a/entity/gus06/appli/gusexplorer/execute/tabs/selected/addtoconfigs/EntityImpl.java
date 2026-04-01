package a.entity.gus06.appli.gusexplorer.execute.tabs.selected.addtoconfigs;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20250905";}


	private Service manager;
	private Service selection;
	private Service choose;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
		choose = Outside.service(this,"gus06.input.choose.dialog.multi");
	}
	
	
	public void e() throws Exception
	{
		File selected = (File) selection.g();
		if(selected==null) return;
		
		List availableNames = new ArrayList();
		
		Map map = (Map) manager.r("*");
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext())
		{
			String name = (String) it.next();
			List list = (List) map.get(name);
			if(!list.contains(selected)) availableNames.add(name);
		}
		Collections.sort(availableNames);
		
		String message = "Please, choose a config";
		String title = "Config chooser";
		List targetNames = (List) choose.t(new Object[]{message, title, availableNames});
		if(targetNames==null) return;
		
		for(int i=0;i<targetNames.size();i++)
		{
			String targetName = (String) targetNames.get(i);
			List targetList = (List) map.get(targetName);
			targetList.add(selected);
			manager.v("persistList", new Object[]{targetName, targetList});
		}
	}
}