package a.entity.gus06.swing.dialog.blocked1.cancel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;
import javax.swing.BorderFactory;
import java.awt.Color;

public class EntityImpl implements Entity, F, V, R {

	public String creationDate() {return "20250309";}
	
	public static final int GAP = 20;

	
	private Service dialog;
	private Service dragFrame;
	
	private JPanel p_buttons;
	private JButton button_cancel;
	
	private boolean ok = false;
	
	

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1");
		dragFrame = Outside.service(this,"gus.x.swing.comp.cust.dragframe");
		
		button_cancel = new JButton("Cancel");
		button_cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {cancel();}
		});
		
		p_buttons = new JPanel(new GridLayout(1,1,GAP,GAP));
		p_buttons.setBorder(BorderFactory.createEmptyBorder(GAP,0,0,0));
		p_buttons.setBackground(Color.WHITE);
		p_buttons.add(button_cancel);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("do") && obj.equals("ok")) {ok();return;}
		if(key.equals("do") && obj.equals("cancel")) {cancel();return;}
		
		dialog.v(key,obj);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("button_cancel")) return button_cancel;
		if(key.equals("keys")) return new String[]{"button_ok","button_cancel"};
		
		throw new Exception("Unknown key: "+key);
	}

	

	public boolean f(Object obj) throws Exception
	{
		JComponent content = (JComponent) obj;
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP));
		panel.setBackground(Color.WHITE);
		
		panel.add(content,BorderLayout.CENTER);
		panel.add(p_buttons,BorderLayout.SOUTH);
		
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