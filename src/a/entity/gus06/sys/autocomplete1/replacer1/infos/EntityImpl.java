package a.entity.gus06.sys.autocomplete1.replacer1.infos;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Set;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20160424";}


	private Service persist;
	private Service setToString;

	public EntityImpl() throws Exception
	{
		persist = Outside.service(this,"gus06.sys.autocomplete1.replacer1.persister");
		setToString = Outside.service(this,"gus06.tostring.set");
	}

	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Set all = (Set) persist.g();
		String infos = (String) setToString.t(all);
		comp.replaceSelection(infos);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String[] input = (String[]) persist.r("input_"+key);
		String[] output = (String[]) persist.r("output_"+key);
		
		String input_ = infos(input);
		String output_ = infos(output);
		
		comp.replaceSelection(infos(key,input_,output_));
	}
	
	
	
	private String infos(String key, String input_, String output_) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		b.append("INPUT FOR ["+key+"]:\n");
		b.append("--------------------\n");
		b.append(input_);
		
		b.append("OUTPUT FOR ["+key+"]:\n");
		b.append("--------------------\n");
		b.append(output_);
		
		return b.toString();
	}
	
	
	private String infos(String[] t) throws Exception
	{
		if(t==null) return "UNDEFINED";
		
		StringBuffer b = new StringBuffer();
		b.append("-- Rule: "+t[0]+"\n");
		b.append("--------------------\n");
		b.append("-- Options: "+t[1]+"\n");
		b.append("--------------------\n");
		return b.toString();
	}
}
