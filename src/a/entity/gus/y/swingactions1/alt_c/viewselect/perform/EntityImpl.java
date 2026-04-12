package a.entity.gus.y.swingactions1.alt_c.viewselect.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240712";}
	
	private Service newViewerString;
	private Service showInFrame;
	private Service now;
	
	public EntityImpl() throws Exception
	{
		newViewerString = Outside.service(this,"factory#gus.y.dataeditor1.string");
		showInFrame = Outside.service(this,"gus.y.compinframe1.show.alwaysontop");
		now = Outside.service(this,"gus.x.time.now.hhmmss1");
	}
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
                String text = getText(comp);

		Object viewer = newViewerString.g();
		((P) viewer).p(text);
		Object viewerComp = ((I) viewer).i();
		
		String title = "UTIL_text#Text snippet at "+now.g();
		showInFrame.v(title,viewerComp);
	}
	
	private String getText(JTextComponent comp)
	{
		String selection = comp.getSelectedText();
		if(selection!=null && !selection.equals("")) return selection;
		return comp.getText();
	}
}
