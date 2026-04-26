package a.entity.gus06.data.viewer.class1.panel.doc;

import a.framework.*;
import javax.swing.JComponent;
import java.net.URL;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140821";}


	private Service shiftPanel;
	private Service urlViewer;
	private Service entityViewer;
	private Service findEntityName;
	
	private Service current;
    


	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		urlViewer = Outside.service(this,"*gus06.data.viewer.class1.panel.doc.url");
		entityViewer = Outside.service(this,"*gus06.data.viewer.class1.panel.doc.entity");
		findEntityName = Outside.service(this,"gus06.convert.classtoentityname");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	
	public Object g() throws Exception
	{return current!=null ? current.g() : null;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Class data = (Class) obj;
		if(data==null)
		{
			shiftPanel.p(null);
			return;
		}
		
		String entityName = (String) findEntityName.t(data);
		if(entityName!=null)
		{
			entityViewer.p(entityName);
			shiftPanel.p(entityViewer.i());
			current = entityViewer;
		}
		else
		{
			urlViewer.p(data);
			shiftPanel.p(urlViewer.i());
			current = urlViewer;
		}
	}
}
