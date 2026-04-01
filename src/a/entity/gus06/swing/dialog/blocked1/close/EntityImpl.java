package a.entity.gus06.swing.dialog.blocked1.close;

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

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20160528";}
	
	public static final int GAP = 20;

	
	private Service dialog;
	private Service dragFrame;
	
	private JPanel p_buttons;
	private JButton button_close;
	
	

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1");
		dragFrame = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		
		button_close = new JButton("OK");
		button_close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {close();}
		});
		
		p_buttons = new JPanel(new GridLayout(1,2,GAP,GAP));
		p_buttons.setBorder(BorderFactory.createEmptyBorder(GAP,0,0,0));
		p_buttons.setBackground(Color.WHITE);
		p_buttons.add(button_close);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("do") && obj.equals("close")) {close();return;}
		throw new Exception("Unknown key: "+key);
	}
	

	public void p(Object obj) throws Exception
	{
		JComponent content = (JComponent) obj;
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP));
		panel.setBackground(Color.WHITE);
		
		panel.add(content,BorderLayout.CENTER);
		panel.add(p_buttons,BorderLayout.SOUTH);
		
		dragFrame.p(panel);
		dialog.p(panel);
	}
	
	
	
	private void close()
	{
		try
		{
			dialog.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"close()",e);}
	}
}
