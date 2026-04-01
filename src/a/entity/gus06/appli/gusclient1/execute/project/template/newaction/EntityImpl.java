package a.entity.gus06.appli.gusclient1.execute.project.template.newaction;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140905";}


	private Service input;
	private Service manager;
	private Service appliPart;
	private Service actionGenerator;
	private Service executeGenerator;


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		appliPart = Outside.service(this,"gus06.appli.gusclient1.project.idtoapplipart");
		actionGenerator = Outside.service(this,"gus06.appli.gusclient1.template.entity.generator");
		executeGenerator = Outside.service(this,"gus06.command.entity.generate");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(invalid(id)) return;
		
		String part1 = (String) input.t("Please, enter action ID:");
		if(invalid(part1)) return;
		
		String display = (String) input.t("Please, enter action default display:");
		if(invalid(display)) return;
		
		
		String part0 = (String) appliPart.t(id);
		
		String entityName_action = part0+"action."+part1;
		String entityName_execute = part0+"execute."+part1;
		
		
		HashMap map = new HashMap();
		map.put("template","entity_action");
		map.put("entityname",entityName_action);
		map.put("execute",entityName_execute);
		map.put("display",display);
		
		actionGenerator.p(map);
		executeGenerator.p(entityName_execute+" e");
	}
	
	
	
	private boolean invalid(String s)
	{return s==null || s.equals("");}
}
