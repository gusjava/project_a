package a.entity.gus06.appli.gusappmonitor.applitab.gui.action;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20190410";}


	private Service consoleGui;
	private Service buildButton;
	private Service executeKill;
	private Service executeStart;
	private Service executeRestart;
	private Service executeExit;
	
	private JPanel panel;
	
	private JButton button_kill;
	private JButton button_start;
	
	private JButton button_orderToExit;
	private JButton button_orderToRestart;

	
	private Object config;
	

	public EntityImpl() throws Exception
	{
		consoleGui = Outside.service(this,"gus06.appli.gusappmonitor.gui.console");
		buildButton = Outside.service(this,"gus06.swing.button.build.runnable");
		executeKill = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.kill");
		executeStart = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.start");
		executeRestart = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.restart");
		executeExit = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.exit");
		
		button_kill = build("Kill",new E(){
			public void e() throws Exception {kill();}
		});
		button_start = build("Launch",new E(){
			public void e() throws Exception {start();}
		});
		
		button_orderToExit = build(">Exit",new E(){
			public void e() throws Exception {orderToExit();}
		});
		button_orderToRestart = build(">Restart",new E(){
			public void e() throws Exception {orderToRestart();}
		});
		
		panel = new JPanel(new GridLayout(1,4));
		
		panel.add(button_start);
		panel.add(button_kill);
		panel.add(button_orderToExit);
		panel.add(button_orderToRestart);
		
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
	
	
	
	
	private void refresh()
	{
		try
		{
			boolean enabled = config!=null && ((F) config).f(null);
			
			button_kill.setEnabled(enabled);
			button_orderToExit.setEnabled(enabled);
			button_orderToRestart.setEnabled(enabled);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void kill()
	{
		try
		{
			if(config==null) return;
			executeKill.p(config);
			println("kill");
		}
		catch(Exception e)
		{Outside.err(this,"kill()",e);}
	}
	
	private void start()
	{
		try
		{
			if(config==null) return;
			executeStart.p(config);
			println("start");
		}
		catch(Exception e)
		{Outside.err(this,"start()",e);}
	}
	
	private void orderToExit()
	{
		try
		{
			if(config==null) return;
			executeExit.p(config);
			println("order to exit");
		}
		catch(Exception e)
		{Outside.err(this,"orderToExit()",e);}
	}
	
	private void orderToRestart()
	{
		try
		{
			if(config==null) return;
			executeRestart.p(config);
			println("order to restart");
		}
		catch(Exception e)
		{Outside.err(this,"orderRestart()",e);}
	}
	
	
		
	private void println(String line) throws Exception
	{consoleGui.p(line);}
}
