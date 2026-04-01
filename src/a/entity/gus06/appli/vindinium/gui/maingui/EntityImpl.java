package a.entity.gus06.appli.vindinium.gui.maingui;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170917";}

	public static final int BUTTON_EDGE = 10;
	
	
	private Service tab;
	private Service configView;
	private Service gameView;
	private Service replay;
	private Service gameSession;
	private Service buildButton;
	
	private JButton button_training;
	private JButton button_arena;
	private JButton button_local;
	
	private JPanel panel;
	private Thread t;



	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		configView = Outside.service(this,"*gus06.appli.vindinium.gui.configview");
		gameView = Outside.service(this,"*gus06.appli.vindinium.gui.gameview");
		replay = Outside.service(this,"*gus06.appli.vindinium.gui.replay");
		gameSession = Outside.service(this,"gus06.appli.vindinium.session.perform");
		buildButton = Outside.service(this,"gus06.appli.vindinium.gui.maingui.buildbutton");
		
		
		button_training = button("Training mode",new ActionListener() {
			public void actionPerformed(ActionEvent e) {startGame(MODE.TRAINING);}
		});
		button_arena = button("Arena mode",new ActionListener() {
			public void actionPerformed(ActionEvent e) {startGame(MODE.ARENA);}
		});
		button_local = button("Local mode",new ActionListener() {
			public void actionPerformed(ActionEvent e) {startGame(MODE.LOCAL);}
		});
		
		JPanel p_buttons = new JPanel(new GridLayout(1,3,BUTTON_EDGE,BUTTON_EDGE));
		p_buttons.setBorder(BorderFactory.createEmptyBorder(BUTTON_EDGE,BUTTON_EDGE,BUTTON_EDGE,BUTTON_EDGE));
		
		p_buttons.add(button_local);
		p_buttons.add(button_training);
		p_buttons.add(button_arena);
		
		
		tab.v("PLAYER1_play#Game play",gameView.i());
		tab.v("PLAYER1_rewind#Game replay",replay.i());
		tab.v("SETTINGS2#Game config",configView.i());
		tab.v("factory3#Bot factory",new JPanel());
		
		
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		panel.add(p_buttons,BorderLayout.SOUTH);
		
		
		gameSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("dataComputed()")) dataComputed();
				if(s.equals("gameFailed()")) gameFailed();
			}
		});
	}


	public Object i() throws Exception
	{return panel;}
	
	
	
	private JButton button(String title, ActionListener l) throws Exception
	{
		JButton button = (JButton) buildButton.t(title);
		button.addActionListener(l);
		return button;
	}
	

	
	private void startGame(String mode)
	{
		if(t!=null && t.isAlive()) return;
		
		setButtonsEnabled(false);
		Runnable r = new GameRunnable(mode);
		t = new Thread(r,"THREAD_"+getClass().getName());
		t.start();
	}
	
	

	private class GameRunnable implements Runnable
	{
		private String mode;
		public GameRunnable(String mode) {this.mode = mode;}
		public void run() {performSession(mode);}
	}
	
	
	private void performSession(String mode)
	{
		try{gameSession.p(mode);}
		catch(Exception e) {Outside.err(this,"performSession(String)",e);}
		setButtonsEnabled(true);
	}
	
	
	
	private void setButtonsEnabled(boolean enabled)
	{
		button_training.setEnabled(enabled);
		button_arena.setEnabled(enabled);
		button_local.setEnabled(enabled);
	}
	
	
	
	private void dataComputed()
	{
		try
		{
			gameView.p(gameSession.g());
		}
		catch(Exception e)
		{Outside.err(this,"dataComputed()",e);}
	}
	
	
	
	
	private void gameFailed()
	{
		try
		{
			Exception exception = (Exception) gameSession.r("exception");
			gameView.v("exception",exception);
		}
		catch(Exception e)
		{Outside.err(this,"gameFailed()",e);}
	}
}
