package a.entity.gus06.appli.pathsearchdemo.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.GridLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150327";}

	public static final int SIZE = 51;
	public static final long SLEEP = 60;


	private Service generate;
	private Service screen;
	private Service pickRandom;
	private Service algoBFS;
	private Service algoDFS;

	private JPanel panel;
	
	private JButton button_gen;
	private JButton button_BFS;
	private JButton button_DFS;
	
	private boolean[][] maze;
	private int[] start;
	private int[] end;
	
	private Thread t;



	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.math.maze.generate.algo1");
		screen = Outside.service(this,"*gus06.appli.pathsearchdemo.gui.screen");
		pickRandom = Outside.service(this,"gus06.math.maze.data.pickrandom");
		algoBFS = Outside.service(this,"gus06.math.maze.solve.algo.breadthfirstsearch");
		algoDFS = Outside.service(this,"gus06.math.maze.solve.algo.depthfirstsearch");
		
		button_gen = button("Generate");
		button_BFS = button("BFS");
		button_DFS = button("DFS");
		
		button_gen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){generate();}
		});
		button_BFS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){start(algoBFS);}
		});
		button_DFS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){start(algoDFS);}
		});
		
		button_BFS.setEnabled(false);
		button_DFS.setEnabled(false);
		
		
		JPanel p_bottom = new JPanel(new GridLayout(1,3));
		p_bottom.add(button_gen);
		p_bottom.add(button_BFS);
		p_bottom.add(button_DFS);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) screen.i(),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		
		algoBFS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("moved()")) moved(algoBFS);
				else if(s.equals("searchOver()")) searchOver(algoBFS);
			}
		});
		
		algoDFS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("moved()")) moved(algoDFS);
				else if(s.equals("searchOver()")) searchOver(algoDFS);
			}
		});
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private JButton button(String display)
	{
		JButton button = new JButton(display);
		button.setFont(button.getFont().deriveFont((float) 15));
		return button;
	}

	
	
	
	private void generate()
	{
		try
		{
			if(t!=null && t.isAlive()) return;
			
			maze = randomMaze();
			start = randomPlace();
			end = randomPlace();
			
			screen.v("maze",maze);
			screen.v("start",start);
			screen.v("end",end);
			
			button_BFS.setEnabled(true);
			button_DFS.setEnabled(true);
		}
		catch(Exception e)
		{Outside.err(this,"generate()",e);}
	}
	
	
	
	
	private void start(Service s)
	{
		try
		{
			if(t!=null && t.isAlive()) return;
			if(maze==null || start==null || end==null) return;
			
			button_gen.setEnabled(false);
			button_BFS.setEnabled(false);
			button_DFS.setEnabled(false);
			
			s.v("maze",maze);
			s.v("start",start);
			s.v("end",end);
			
			screen.v("path",null);
			
			t = new Thread(new Runnable() {
				public void run() {
					try {s.e();}
					catch(Exception e)
					{e.printStackTrace();}
				}
			},"THREAD_"+getClass().getName());
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"start(Service)",e);}
	}
	

	private void moved(Service s)
	{
		try
		{
			int[] p = (int[]) s.r("current");
			screen.v("position",p);
			
			boolean[][] state = (boolean[][]) s.r("state");
			screen.v("state",state);
			
			Thread.sleep(SLEEP);
		}
		catch(Exception e)
		{Outside.err(this,"moved(Service)",e);}
	}
	
	
	
	private void searchOver(Service s)
	{
		try
		{
			int[][] path = (int[][]) s.r("path");
			screen.v("path",path);
			
			button_gen.setEnabled(true);
			button_BFS.setEnabled(true);
			button_DFS.setEnabled(true);
		}
		catch(Exception e)
		{Outside.err(this,"searchOver(Service)",e);}
	}
	
	
	
	



	private boolean[][] randomMaze() throws Exception
	{return (boolean[][]) generate.r(""+SIZE);}

	private int[] randomPlace() throws Exception
	{return (int[]) pickRandom.t(maze);}
}
