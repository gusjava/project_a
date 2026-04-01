package a.entity.gus06.appli.gusappmonitor.applitab.gui.debug;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.io.PrintStream;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20200129";}


	private Service console;
	private Service buildButton;
	private Service executeThreadState;
	private Service executeStracktrace;
	
	private JPanel panel;
	private PrintStream p;
	
	private JButton button_threadState;
	private JButton button_guiState;

	private Object config;
	

	public EntityImpl() throws Exception
	{
		console = Outside.service(this,"*gus06.io.printstream.holder.area");
		buildButton = Outside.service(this,"gus06.swing.button.build.runnable");
		executeThreadState = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.debug.threadstate");
		executeStracktrace = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.debug.stacktrace");
		
		p = (PrintStream) console.g();
		
		button_threadState = build("Thread state",new E(){
			public void e() throws Exception {executeThreadState();}
		});
		button_guiState = build("Stacktrace",new E(){
			public void e() throws Exception {executeStracktrace();}
		});
		
		JPanel panelBottom = new JPanel(new GridLayout(1,2));
		
		panelBottom.add(button_threadState);
		panelBottom.add(button_guiState);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) console.i(),BorderLayout.CENTER);
		panel.add(panelBottom,BorderLayout.SOUTH);
		
		refresh();
	}
	
	
	
	private JButton build(String text, E e) throws Exception
	{
		JButton b = (JButton) buildButton.t(e);
		b.setText(text);
		return b;
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(config!=null) ((S)config).removeActionListener(this);
		config = obj;
		if(config!=null) ((S)config).addActionListener(this);
		refresh();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("modified()")) refresh();
		if(s.equals("debugInfoReceived()")) debugInfoReceived();
	}
	
	
	
	
	private void refresh()
	{
		try
		{
			boolean enabled = config!=null && ((F) config).f(null);
			
			button_threadState.setEnabled(enabled);
			button_guiState.setEnabled(enabled);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void debugInfoReceived()
	{
		try
		{
			if(config==null) return;
			String info = (String) ((R) config).r("debugInfo");
			p.println(info);
		}
		catch(Exception e)
		{Outside.err(this,"debugInfoReceived()",e);}
	}

	
	
	
	private void executeStracktrace()
	{
		try
		{
			if(config==null) return;
			console.e();
			executeStracktrace.p(config);
		}
		catch(Exception e)
		{Outside.err(this,"executeStracktrace()",e);}
	}
	
	private void executeThreadState()
	{
		try
		{
			if(config==null) return;
			console.e();
			executeThreadState.p(config);
		}
		catch(Exception e)
		{Outside.err(this,"executeThreadState()",e);}
	}
}
