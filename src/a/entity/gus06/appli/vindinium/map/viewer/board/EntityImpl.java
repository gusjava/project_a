package a.entity.gus06.appli.vindinium.map.viewer.board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl extends S1 implements Entity, P, G, I, MouseListener, MouseMotionListener, KeyListener {

	public String creationDate() {return "20170917";}

	private Service screen;
	private Service buildImage;
	private Service pointToPos;
	private Service invertTiles;
	private Service changeTile;
	private Service rebuild;
	private Service fullScreen;

	private int[][] tiles;
	private JPanel panel;
	
	private int[] start;
	private int[] end;
	
	private MouseEvent lastMouseEvent;


	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		buildImage = Outside.service(this,"gus06.appli.vindinium.map.viewer.board.buildimage");
		pointToPos = Outside.service(this,"gus06.appli.vindinium.map.viewer.board.mousetoposition");
		invertTiles = Outside.service(this,"gus06.appli.vindinium.map.viewer.board.inverttiles");
		changeTile = Outside.service(this,"gus06.appli.vindinium.map.viewer.board.changetile");
		rebuild = Outside.service(this,"gus06.appli.vindinium.map.viewer.board.rebuild");
		fullScreen = Outside.service(this,"gus06.awt.fullscreen.onkey.space");
		
		panel = (JPanel) screen.i();
		panel.setFocusable(true);
		
		panel.addMouseMotionListener(this);
		panel.addMouseListener(this);
		panel.addKeyListener(this);
		
		fullScreen.p(panel);
	}


	public Object i() throws Exception
	{return panel;}



	public void p(Object obj) throws Exception
	{
		tiles = (int[][]) obj;
		updateScreen();
	}
	
	
	
	public Object g() throws Exception 
	{return tiles;}
	
	
	
	
	private void updateScreen()
	{
		try
		{screen.p(buildImage.t(tiles));}
		catch(Exception e)
		{Outside.err(this,"updateScreen()",e);}
	}
	
	
	private void boardModified()
	{send(this,"boardModified()");}
	
	

	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e)
	{
		lastMouseEvent = e;
	}
	
	
	public void mouseEntered(MouseEvent e)
	{
		panel.requestFocusInWindow();
	}
	
	public void mouseExited(MouseEvent e) {}

	
	public void mousePressed(MouseEvent e)
	{
		start = pointToPos(e);
	}
	

	public void mouseReleased(MouseEvent e) 
	{
		end = pointToPos(e);
		invertTiles();
	}
	
	
	public void mouseClicked(MouseEvent e)
	{
		changeTile(e);
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		int c = e.getKeyCode();
		
		if(c==KeyEvent.VK_F1) rebuild();
		else if(c==KeyEvent.VK_W) changeTile("w");
		else if(c==KeyEvent.VK_M) changeTile("m");
		else if(c==KeyEvent.VK_A) changeTile("a");
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	
	
	private int[] pointToPos(MouseEvent evt)
	{
		if(evt==null) return null;
		try{return (int[]) pointToPos.t(new Object[]{tiles,panel,evt});}
		catch(Exception e){Outside.err(this,"pointToPos(MouseEvent)",e);}
		return null;
	}
	
	
	
	private void invertTiles()
	{
		try
		{
			int[][] r = (int[][]) invertTiles.t(new Object[]{tiles,start,end});
			if(r!=null) updateTiles(r);
		}
		catch(Exception e)
		{Outside.err(this,"invertTiles()",e);}
	}



	private void changeTile(MouseEvent evt)
	{
		try
		{
			int[] p = pointToPos(evt);
			int[][] r = (int[][]) changeTile.t(new Object[]{tiles,p});
			if(r!=null) updateTiles(r);
		}
		catch(Exception e)
		{Outside.err(this,"changeTile(MouseEvent)",e);}
	}
	
	
	
	private void changeTile(String type)
	{
		try
		{
			int[] p = pointToPos(lastMouseEvent);
			int[][] r = (int[][]) changeTile.t(new Object[]{tiles,p,type});
			if(r!=null) updateTiles(r);
		}
		catch(Exception e)
		{Outside.err(this,"changeTile(String)",e);}
	}
	
	
	
	private void rebuild()
	{
		try
		{
			rebuild.p(tiles);
			updateTiles(tiles);
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}
	
	
	
	private void updateTiles(int[][] tiles)
	{
		this.tiles = tiles;
		updateScreen();
		boardModified();
	}
	
}
