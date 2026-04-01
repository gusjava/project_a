package a.entity.gus06.jdbc.gui.cx1.bar.userlabel;

import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150622";}


	private Service userDisplay;
	private Service repaint;
	private Service findCx;
	
	private JLabel label;
	

	public EntityImpl() throws Exception
	{
		userDisplay = Outside.service(this,"gus06.jdbc.connection.grants.display");
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		
		label = new JLabel(" ");
	}
	

	public Object i() throws Exception
	{return label;}


	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) findCx.t(obj);
		String display = (String) userDisplay.t(cx);
		repaint.v(display,label);
	}
}
