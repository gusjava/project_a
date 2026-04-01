package a.entity.gus06.appli.gusexplorer.icons.importer;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201206";}
	

	private Service importIcon;
	private Service input;
	private Service confirm;
	private Service clipboardString;

	public EntityImpl() throws Exception
	{
		importIcon = Outside.service(this,"gus06.icon.importer");
		input = Outside.service(this,"gus06.input.text.dialog");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		clipboardString = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	public void p(Object obj) throws Exception
	{
		String key = findKey();
		if(key==null) return;
		
		importIcon.v(key,obj);
		clipboardString.p(key);
	}
	
	
	
	private String findKey() throws Exception
	{
		String key = (String) input.t("Enter icon's key:");
		if(key==null || key.equals("")) return null;
		
		boolean used = importIcon.f(key);
		if(used)
		{
			boolean replace = confirm.f("Key already used: "+key+"\nReplace?");
			if(!replace) return null;
		}
		return key;
	}
}