package a.entity.gus06.map.string.replacer.ask;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190429";}


	private Service perform;
	private Service input;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.map.string.replacer");
		input = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String seq = (String) input.t("Enter search string:");
		if(seq==null || seq.equals("")) return;
		
		String repl = (String) input.t("Enter replacement string:");
		if(repl==null) return;
		
		perform.p(new Object[]{obj,seq,repl});
	}
}
