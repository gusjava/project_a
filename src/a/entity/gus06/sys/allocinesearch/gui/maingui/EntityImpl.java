package a.entity.gus06.sys.allocinesearch.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20200920";}
	
	
	private Service findResultList;
	private Service inputHolder;
	private Service resultView;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		findResultList = Outside.service(this,"gus06.web.allocine.convert.videofiletoresultlist");
		inputHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		resultView = Outside.service(this,"*gus06.sys.allocinesearch.gui.resultview");
		
		JComponent inputComp = (JComponent) inputHolder.i();
		inputComp.setFont(inputComp.getFont().deriveFont((float)16));
		
		inputHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{updateGui();}
		});
		
		resultView.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(inputComp,BorderLayout.NORTH);
		panel.add((JComponent) resultView.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	public Object g() throws Exception
	{return resultView.g();}

	
	public void p(Object obj) throws Exception
	{
		String input = (String) obj;
		inputHolder.p(input);
		updateGui();
	}
	
	
	
	private void updateGui()
	{
		try
		{
			String input = (String) inputHolder.g();
			Object resultList = findResultList.t(input);
			resultView.p(resultList);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}


	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
