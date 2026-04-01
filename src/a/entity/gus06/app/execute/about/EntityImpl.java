package a.entity.gus06.app.execute.about;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20140803";}


	private Service dialogPopup;
	private Service aboutPanel;
	


	public EntityImpl() throws Exception
	{
		dialogPopup = Outside.service(this,"gus06.swing.dialog.popup1");
		aboutPanel = Outside.service(this,"*gus06.app.execute.about.panel");
	}
	
	
	public void e() throws Exception
	{dialogPopup.p(aboutPanel.i());}
}
