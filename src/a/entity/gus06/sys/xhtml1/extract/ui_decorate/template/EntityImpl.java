package a.entity.gus06.sys.xhtml1.extract.ui_decorate.template;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220908";}
	
	public static final String RULE_TEMPLATE = "template=\"*\"";
	public static final String RULE_QUOTE = "'*'";


	private Service extractTag;
	private Service extract1;
	private Service extract2;


	public EntityImpl() throws Exception
	{
		extractTag = Outside.service(this,"gus06.sys.xhtml1.extract.ui_decorate.tag");
		extract1 = Outside.service(this,"gus06.string.extract.extract1.find.s.f");
		extract2 = Outside.service(this,"gus06.string.extract.extract1.find.s.a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) extractTag.t(obj);
		
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			String tag = (String) list.get(i);
			String template = (String) extract1.t(new String[]{tag, RULE_TEMPLATE});
			if(template==null) throw new Exception("template attribute not found for tag: "+tag);
			
			if(template.contains("#{"))
			{
				List l = (List) extract2.t(new String[]{template, RULE_QUOTE});
				list1.addAll(l);
			}
			else list1.add(template);
		}
		return list1;
	}
}