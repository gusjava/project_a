package a.entity.gus06.sys.objfactory1.rulemap1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191122";}


	private Service ruleBoolean;
	private Service ruleClass;
	private Service ruleColor;
	private Service ruleDate;
	private Service ruleDouble;
	private Service ruleEntity;
	private Service ruleFile;
	private Service ruleFont;
	private Service ruleIcon;
	private Service ruleImage;
	private Service ruleInt;
	private Service ruleLabel;
	private Service ruleNew;
	private Service rulePanel;
	private Service ruleString;
	private Service ruleUrl;
	private Service ruleView;

	private Map map;
	

	public EntityImpl() throws Exception
	{
		ruleBoolean = Outside.service(this,"gus06.find.boolean1");
		ruleClass = Outside.service(this,"gus06.find.class1");
		ruleColor = Outside.service(this,"gus06.find.color");
		ruleDate = Outside.service(this,"gus06.find.date");
		ruleDouble = Outside.service(this,"gus06.find.double1");
		ruleEntity = Outside.service(this,"gus06.find.entity");
		ruleFile = Outside.service(this,"gus06.find.file");
		ruleFont = Outside.service(this,"gus06.find.font");
		ruleIcon = Outside.service(this,"gus06.find.icon");
		ruleImage = Outside.service(this,"gus06.find.image");
		ruleInt = Outside.service(this,"gus06.find.integer");
		ruleLabel = Outside.service(this,"gus06.find.jlabel");
		ruleNew = Outside.service(this,"gus06.find.obj1");
		rulePanel = Outside.service(this,"gus06.find.jpanel");
		ruleString = Outside.service(this,"gus06.tostring.tostring1");
		ruleUrl = Outside.service(this,"gus06.find.url");
		ruleView = Outside.service(this,"gus06.data.viewer.object.factory.comp");
		
		map = new HashMap();
		
		map.put("boolean",ruleBoolean);
		map.put("class",ruleClass);
		map.put("color",ruleColor);
		map.put("date",ruleDate);
		map.put("double",ruleDouble);
		map.put("entity",ruleEntity);
		map.put("file",ruleFile);
		map.put("font",ruleFont);
		map.put("icon",ruleIcon);
		map.put("image",ruleImage);
		map.put("int",ruleInt);
		map.put("label",ruleLabel);
		map.put("new",ruleNew);
		map.put("panel",rulePanel);
		map.put("string",ruleString);
		map.put("url",ruleUrl);
		map.put("view",ruleView);
	}
	
	
	public Object g() throws Exception
	{return map;}
}
