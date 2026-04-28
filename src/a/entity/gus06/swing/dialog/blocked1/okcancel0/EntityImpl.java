package a.entity.gus06.swing.dialog.blocked1.okcancel0;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;
import javax.swing.BorderFactory;
import java.awt.Color;

public class EntityImpl implements Entity, F, V {

	public String creationDate() {return "20160509";}
	
	public static final int GAP = 20;

	private Service dialog;
	private Service dragFrame;
	
	private boolean ok = false;

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1");
		dragFrame = Outside.service(this,"gus.x.swing.comp.cust.dragframe");
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("do") && obj.equals("ok")) {ok();return;}
		if(key.equals("do") && obj.equals("cancel")) {cancel();return;}
		
		dialog.v(key,obj);
	}
	
	public boolean f(Object obj) throws Exception
	{
		JComponent content = (JComponent) obj;
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP));
		panel.setBackground(Color.WHITE);
		
		panel.add(content,BorderLayout.CENTER);
		
		dragFrame.p(panel);
		
		ok = false;
		dialog.p(panel);
		return ok;
	}
	
	private void cancel()
	{
		try
		{
			ok = false;
			dialog.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}
	
	private void ok()
	{
		try
		{
			ok = true;
			dialog.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"ok()",e);}
	}
}
