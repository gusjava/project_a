package a.entity.gus06.swing.tree.cust.renderer.obj1.finddisplay;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190310";}


	private Service handleIcon;
	private Service handleText;
	
	public EntityImpl() throws Exception
	{
		handleIcon = Outside.service(this,"gus06.swing.tree.cust.renderer.obj1.finddisplay.icon");
		handleText = Outside.service(this,"gus06.swing.tree.cust.renderer.obj1.finddisplay.text");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "null";
		if(obj instanceof G) obj = ((G)obj).g();
		
		String iconId = (String) handleIcon.t(obj);
		String text = (String) handleText.t(obj);
		
		if(iconId==null) return text;
		return iconId+"#"+text;
	}
}