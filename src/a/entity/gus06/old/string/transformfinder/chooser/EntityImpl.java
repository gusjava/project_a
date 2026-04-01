package a.entity.gus06.old.string.transformfinder.chooser;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150928";}

	public static final String MESSAGE = "Choose keyword for string transform";

	private Service chooseKeyword;
	private Service findTransform;
	
	
	public EntityImpl() throws Exception
	{
		chooseKeyword = Outside.service(this,"gus06.input.text.dialog");
		findTransform = Outside.service(this,"gus06.string.transformfinder.fromkeyword");
	}
	
	
	public Object g() throws Exception
	{
		String v = (String) chooseKeyword.t(MESSAGE);
		if(v==null || v.equals("")) return null;
		return findTransform.t(v);
	}
}