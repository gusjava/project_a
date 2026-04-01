package a.entity.gus06.sys.direditor1.gui.main;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P {

	public String creationDate() {return "20200530";}


	private Service editor;
	private Service showInfo;
	private Service showError;

	private JPanel panel;
	private JButton button;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.sys.direditor1.gui.editor");
		showInfo = Outside.service(this,"gus06.swing.optionpane.showmessage.error");
		showError = Outside.service(this,"gus06.swing.optionpane.showmessage.error");
		
		button = new JButton("Perform changes");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) editor.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{editor.p(obj);}
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			String result = (String) editor.g();
			p(showInfo,result);
		}
		catch(Exception e)
		{
			p(showError,e.getMessage());
			Outside.err(this,"perform()",e);
		}
		performed();
	}
	
	
	
	private void p(P p, Object obj)
	{
		try{p.p(obj);}
		catch(Exception e)
		{Outside.err(this,"p(P,Object)",e);}
	}
	
	
	private void performed()
	{send(this,"performed()");}
}
