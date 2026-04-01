package a.entity.gus06.appli.democlustering.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170107";}
	
	public static final int EDGE = 15;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static final int NUMBER = 200;
	public static final int CLUSTERNB = 7;
	public static final int POINTSIZE = 3;
	public static final int CROSSLENGTH = 12;
	public static final Color POINTCOLOR = Color.BLACK;


	private Service screen;
	private Service engine;
	private Service rebuild;


	private JPanel panel;
	private JButton button1;
	private JButton button2;
	private JComponent screenComp;
	
	private Map map;
	
	

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.points");
		engine = Outside.service(this,"gus06.sys.clustering1.engine.hac");
		rebuild = Outside.service(this,"gus06.appli.democlustering.rebuild.map");
		
		screen.v("dim",new int[]{WIDTH,HEIGHT});
		screen.v("pointsize",""+POINTSIZE);
		screen.v("crosslength",""+CROSSLENGTH);
		
		screenComp = (JComponent) screen.i();
		
		button1 = new JButton("Generate");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{generate();}
		});
		
		button2 = new JButton("Clusterize");
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{clusterize();}
		});
		
		Border b1 = BorderFactory.createLoweredBevelBorder();
		Border b2 = BorderFactory.createEmptyBorder(EDGE,EDGE,EDGE,EDGE);
		Border bb = BorderFactory.createCompoundBorder(b2,b1);
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(screenComp,BorderLayout.CENTER);
		panel1.setBorder(bb);
		
		JPanel panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(button1);
		panel2.add(button2);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel1,BorderLayout.CENTER);
		panel.add(panel2,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	
	
	
	private void generate()
	{
		try
		{
			map = new HashMap();
			
			for(int i=0;i<NUMBER;i++)
			{
				int x = (int) (Math.random()*WIDTH);
				int y = (int) (Math.random()*HEIGHT);
				
				int[] p = new int[]{x,y};
				map.put(p,POINTCOLOR);
			}
			
			screen.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"generate()",e);}
	}


	private void clusterize()
	{
		try
		{
			if(map==null) return;
			List points = new ArrayList(map.keySet());
			
			List[] r = (List[]) engine.t(new Object[]{points,CLUSTERNB});
			List sets = r[0];
			
			map = (Map) rebuild.t(new List[]{points,sets});
			screen.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"clusterize()",e);}
	}
}
