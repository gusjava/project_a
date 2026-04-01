package a.entity.gus06.appli.pathsearchdemo.gui.screen;

import javax.swing.*;
import java.awt.*;

public class ScreenJPanel extends JPanel {

	public static final Color COLOR_WALL = Color.BLACK;
	public static final Color COLOR_DISCOVERED = Color.LIGHT_GRAY;
	public static final Color COLOR_EMPTY = Color.WHITE;
	public static final Color COLOR_START = Color.GREEN;
	public static final Color COLOR_END = Color.RED;
	public static final Color COLOR_POSITION = Color.CYAN;
	public static final Color COLOR_PATH = new Color(255,238,178);
	
    private boolean[][] maze;
    private boolean[][] state;
    private int[] start;
    private int[] end;
    private int[] position;
    private int[][] path;
    
    private int x_maze;
    private int y_maze;
    
    
    private int x0;
    private int y0;
    private double c;
    
    


    public void setMaze(boolean[][] maze)
    {
    	this.maze = maze;
    	x_maze = maze.length;
    	y_maze = maze[0].length;
        
    	this.state = null;
    	this.start = null;
    	this.end = null;
    	this.position = null;
    	this.path = null;
    }
    
    public void setState(boolean[][] state)
    {this.state = state;}

    public void setStart(int[] start)
    {this.start = start;}
    
    public void setEnd(int[] end)
    {this.end = end;}

    public void setPosition(int[] position)
    {this.position = position;}

    public void setPath(int[][] path)
    {this.path = path;}
    


    public void paintComponent(Graphics g)
    {  
    	super.paintComponent(g);
        if(maze!=null) paintMaze(g);
    }
    
    
    
    
    private void paintMaze(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D)g;
        
        double h = ((double)getHeight()) / ((double)x_maze);
        double w = ((double)getWidth()) / ((double)y_maze);
        
        if(h>w)
        {
        	c = w;
        	x0 = (int) ((getHeight()-(x_maze*c))*0.5);
        	y0 = 0;
        }
        else
        {
        	c = h;
        	x0 = 0;
        	y0 = (int) ((getWidth()-(y_maze*c))*0.5);
        }
        
        g2.setColor(COLOR_EMPTY);
        g2.fillRect(y0,x0,(int)(y_maze*c),(int)(x_maze*c));
        
        g2.setColor(COLOR_DISCOVERED);
        fillMaze(g2,state);
        
        g2.setColor(COLOR_WALL);
        fillMaze(g2,maze);
        
        g2.setColor(COLOR_POSITION);
        fillCell(g2,position);
        
        g2.setColor(COLOR_PATH);
        fillPath(g2,path);
        
        g2.setColor(COLOR_START);
        fillCell(g2,start);
        
        g2.setColor(COLOR_END);
        fillCell(g2,end);
    }
    
    
    
    private void fillCell(Graphics2D g2, int[] pos)
    {
    	if(pos!=null)
    	g2.fillRect(y0+(int)(c*pos[1]),x0+(int)(c*pos[0]),(int)c+1,(int)c+1);
    }
    
    
    private void fillPath(Graphics2D g2, int[][] path)
    {
    	if(path==null) return;
    	for(int i=0;i<path.length;i++)
    		fillCell(g2,path[i]);
    }
    
    
    private void fillMaze(Graphics2D g2, boolean[][] m)
    {
    	if(m==null) return;
    	
    	for(int i=0;i<x_maze;i++)
        for(int j=0;j<y_maze;j++)
        if(!m[i][j]) fillCell(g2,new int[]{i,j});
    }
}
