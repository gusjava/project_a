package a.entity.gus06.appli.entitytester.gui.stringtransform;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.awt.GridLayout;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150920";}


	private Service buildCombo;
	private Service buildPanel;
	private Service comboPersister;
	
	private JButton btn_perform;
	private JButton btn_outputToInput;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		buildCombo = Outside.service(this,"*gus06.appli.entitytester.gui.stringtransform.combo");
		buildPanel = Outside.service(this,"*gus06.appli.entitytester.gui.stringtransform.panel");
		comboPersister = Outside.service(this,"gus06.swing.combobox.persister.index");
		
		btn_perform = new JButton("Perform");
		btn_perform.addActionListener(this);
		
		btn_outputToInput = new JButton("Ouput to input");
		btn_outputToInput.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{outputToInput();}
		});
		
		JPanel p_buttons = new JPanel(new GridLayout(1,2));
		p_buttons.add(btn_perform);
		p_buttons.add(btn_outputToInput);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) buildCombo.i(),BorderLayout.NORTH); 
		panel.add((JComponent) buildPanel.i(),BorderLayout.CENTER); 
		panel.add(p_buttons,BorderLayout.SOUTH);
		
		comboPersister.v(getClass().getName()+"_combo",buildCombo.i());
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	
	private void perform()
	{
		try
		{
			T t = (T) buildCombo.g();
			if(t==null) return;
			
			buildPanel.p(t);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	private void outputToInput()
	{
		try
		{
			buildPanel.e();
		}
		catch(Exception e)
		{Outside.err(this,"outputToInput()",e);}
	}


}
