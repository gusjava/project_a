package a.entity.gus06.appli.gusclient1.execute.project.template.maingui.panel;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140905";}


	private Service manager;
	private Service mainGuiName;
	private Service generator;
	private Service setProp;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		mainGuiName = Outside.service(this,"gus06.appli.gusclient1.project.idtoentityname.maingui");
		generator = Outside.service(this,"gus06.appli.gusclient1.template.entity.generator");
		setProp = Outside.service(this,"gus06.appli.gusclient1.project.config.setprop");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(invalid(id)) return;
		
		String entityName = (String) mainGuiName.t(id);
		
		HashMap map = new HashMap();
		map.put("template","entity_panel");
		map.put("entityname",entityName);
		
		generator.p(map);
		
		setProp.v("app.maingui",entityName);
	}
	
	
	
	private boolean invalid(String s)
	{return s==null || s.equals("");}
}
