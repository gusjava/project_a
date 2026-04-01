package a.entity.gus06.sys.autocomplete1.replacer1.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160424";}


	private Service persist;
	private Service extractor;
	private Service generator;


	public EntityImpl() throws Exception
	{
		persist = Outside.service(this,"gus06.sys.autocomplete1.replacer1.persister");
		extractor = Outside.service(this,"gus06.string.extract.extractor1");
		generator = Outside.service(this,"gus06.string.generate.generator1");
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String[] input = (String[]) persist.r("input_"+key);
		String[] output = (String[]) persist.r("output_"+key);
		
		if(input==null && output==null) throw new Exception("Key not found: "+key);
		
		String data = comp.getSelectedText();
		String data1 = compute(data,input,output);
		
		comp.replaceSelection(data1);
	}
	
	
	private String compute(String data, String[] input, String[] output) throws Exception
	{
		Object r = input==null ? data : extractor.t(new String[]{data,input[0],input[1]});
		return output==null ? (String) r : (String) generator.t(new Object[]{r,output[0],output[1]});
	}
}
