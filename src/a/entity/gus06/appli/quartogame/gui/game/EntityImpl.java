package a.entity.gus06.appli.quartogame.gui.game;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191115";}


	private Service manager;
	private Service board;

	private JPanel panel;
	private JButton button;
	
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.quartogame.manager");
		board = Outside.service(this,"*gus06.appli.quartogame.gui.board");
		
		button = new JButton("Start new game");
		button.setFont(button.getFont().deriveFont((float) 15));
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{startNewGame();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) board.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		manager.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{updateBoard();}
		});
		board.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{performMove();}
		});
		
		board.p(manager.g());
	}
	
	
	public Object i() throws Exception
	{return panel;}


	
	
	
	private void startNewGame()
	{
		try{manager.e();}
		catch(Exception e)
		{Outside.err(this,"startNewGame()",e);}
	}
	
	
	
	private void updateBoard()
	{
		try{board.p(manager.g());}
		catch(Exception e)
		{Outside.err(this,"updateBoard()",e);}
	}
	
	
	private void performMove()
	{
		try{manager.p(board.g());}
		catch(Exception e)
		{Outside.err(this,"performMove()",e);}
	}
}
