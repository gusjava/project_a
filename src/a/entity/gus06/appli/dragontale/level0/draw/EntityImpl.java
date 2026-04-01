package a.entity.gus06.appli.dragontale.level0.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}

	public static final Color TITLECOLOR = new Color(128, 0, 0);
	public static final Font TITLEFONT = new Font("Century Gothic",Font.PLAIN,28);
	public static final String TITLE = "Dragon Tale";
	
	
	public static final Color MENUCOLOR = Color.RED;
	public static final Color MENUCOLOR_SELECTED = Color.BLACK;
	public static final Font MENUFONT = new Font("Arial",Font.PLAIN,12);
	public static final String[] MENU = {"Start","Help","Quit"};
	

	private Service background;
	private Service controller;

	
	public EntityImpl() throws Exception
	{
		background = Outside.service(this,"gus06.appli.dragontale.level0.draw.background");
		controller = Outside.service(this,"gus06.appli.dragontale.level0.controller");
	}


	
	

	public void p(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		background.p(image);
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		g.setColor(TITLECOLOR);
		g.setFont(TITLEFONT);
		g.drawString(TITLE, 80, 70);
		
		int selected = (int) controller.r("selected");
		
		g.setFont(MENUFONT);
		for(int i=0;i<MENU.length;i++)
		{
			g.setColor(i == selected ? MENUCOLOR_SELECTED : MENUCOLOR);
			g.drawString(MENU[i], 145, 140+i*15);
		}
	}
}
