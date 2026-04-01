package a.entity.gus06.sys.autocomplete1.replacer1.output;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160424";}
	
	

	private Service input;
	private Service persist;
	
	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		persist = Outside.service(this,"gus06.sys.autocomplete1.replacer1.persister");
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String rule = comp.getSelectedText();
		
		String options = (String) input.t("Type options:");
		if(options==null) return;
		
		persist.v("output_"+key,new String[]{rule,options});
	}
}
