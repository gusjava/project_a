package a.entity.gus06.input.text.dialog.change;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201121";}


	private Service input;

	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String message = o[0];
		String oldValue = o[1];
		
		String newValue = (String) input.t(new String[]{message,oldValue});
		if(newValue==null || newValue.equals("")) return null;
		if(newValue.equals(oldValue)) return null;
		
		return newValue;
	}
}