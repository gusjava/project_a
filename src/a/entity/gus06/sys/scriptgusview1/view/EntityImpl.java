package a.entity.gus06.sys.scriptgusview1.view;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class EntityImpl implements Entity, ActionListener, I, P, G, R, E, Runnable {

	public String creationDate() {return "20250319";}

	
	private Service viewer;
	private Service shiftPanel;
	private Service console;
	private Service putAction;
	private Service executeAfter;
	
	private JSplitPane split;
	private JPanel panel;
	private JTextComponent comp;
	private JButton button;
	
	private String src;
	private Thread t;
	private EndExecute endExecute;
	
	private int state = 0;
	
	

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.string");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		console = Outside.service(this,"*gus06.sys.scriptgusview1.view.console");
		putAction = Outside.service(this,"gus06.swing.textcomp.cust.putaction");
		executeAfter = Outside.service(this,"gus06.thread.start.executeafter");
		
		comp = (JTextComponent) viewer.r("comp");
		
		button = new JButton("Execute");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		split = new JSplitPane();
		split.setDividerLocation(400);
		split.setLeftComponent(panel);
		split.setRightComponent((JComponent) console.i());
		
		shiftPanel.p(split);
		
		E executeInv = (E) this::nextState;
		E executeLapse = (E) this::initLapse;
		
		putAction.p(new Object[]{comp,this,"F12"});
		putAction.p(new Object[]{comp,executeInv,"F11"});
		putAction.p(new Object[]{comp,executeLapse,"F10"});
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{return src;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("src")) return src;
		if(key.equals("keys")) return new String[]{"comp","src"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(endExecute!=null) endExecute.cancel();
		endExecute = null;
		t = null;
		
		src = (String) obj;
		viewer.p(src);
		console.p(null);
		setStateButtonEnd();
	}


	public void actionPerformed(ActionEvent e)
	{startScript();}
	
	
	public void e() throws Exception
	{startScript();}
	
	
	
	
	private void startScript()
	{
		try
		{
			if(t!=null && t.isAlive()) return;
			t = new Thread(this,"THREAD_"+getClass().getName());
			
			endExecute = new EndExecute();
			executeAfter.p(new Object[]{t,endExecute});
		}
		catch(Exception e)
		{Outside.err(this,"startScript()",e);}
	}
	
	
	
	private void initLapse() throws Exception
	{
		console.v("lapse","10");
	}
	
	
	
	private void nextState() throws Exception
	{
		state++;
		if(state==3) state = 0;
		
		switch(state)
		{
			case 0:
			split.setDividerLocation(400);
			split.setLeftComponent(panel);
			split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			shiftPanel.p(split);
			break;
			
			case 1:
			split.setDividerLocation(400);
			split.setLeftComponent(panel);
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
			shiftPanel.p(split);
			break;
			
			case 2:
			shiftPanel.p(panel);
			break;
		}
	}
	
	
	
	private void setStateButtonStart()
	{
		button.setForeground(Color.BLUE);
		button.setText("Executing...");
		button.setFont(button.getFont().deriveFont(Font.BOLD));
	}
	
	private void setStateButtonEnd()
	{
		button.setFont(button.getFont().deriveFont(Font.PLAIN));
		button.setForeground(Color.BLACK);
		button.setText("Execute");
	}
	
	
	public void run()
	{
		setStateButtonStart();
		
		try
		{
			console.p(src);
			Thread.sleep(100);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		
		setStateButtonEnd();
	}
	
	
	private class EndExecute implements E
	{
		private boolean cancelled = false;
		public void cancel() {cancelled = true;}
		
		public void e() throws Exception
		{
			if(cancelled) return;
			setStateButtonEnd();
		}
	}
}