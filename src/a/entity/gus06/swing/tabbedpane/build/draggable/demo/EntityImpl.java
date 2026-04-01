package a.entity.gus06.swing.tabbedpane.build.draggable.demo;

import a.framework.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Font;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141109";}
	
	public static final Color BACKGROUND = (new JLabel()).getBackground();

	private Service builddraggable;
	
	private Icon icon_red;
	private Icon icon_blue;
	private Icon icon_yellow;
	private Icon icon_green;
	private Icon icon_gray;
	private Icon icon_black;
	
	private JTabbedPane tabbedPane;
	
	
	public EntityImpl() throws Exception
	{
		builddraggable = Outside.service(this,"gus06.swing.tabbedpane.build.draggable");
		tabbedPane = (JTabbedPane) builddraggable.i();
		
		icon_red = (Icon) Outside.resource(this,"icon#COLOR_red");
		icon_blue = (Icon) Outside.resource(this,"icon#COLOR_blue");
		icon_yellow = (Icon) Outside.resource(this,"icon#COLOR_yellow");
		icon_green = (Icon) Outside.resource(this,"icon#COLOR_green");
		icon_gray = (Icon) Outside.resource(this,"icon#COLOR_gray");
		icon_black = (Icon) Outside.resource(this,"icon#COLOR_black");
		
		tabbedPane.addTab("Rouge",icon_red,area(Color.RED));
		tabbedPane.addTab("Bleu",icon_blue,area(Color.BLUE));
		tabbedPane.addTab("Jaune",icon_yellow,area(Color.YELLOW));
		tabbedPane.addTab("Vert",icon_green,area(Color.GREEN));
		tabbedPane.addTab("Gris",icon_gray,area(Color.GRAY));
		tabbedPane.addTab("Noir",icon_black,area(Color.BLACK));
		
		tabbedPane.setTabComponentAt(2,label(icon_yellow,"Jaune",Color.YELLOW));
	}



	public Object i() throws Exception
	{return tabbedPane;}


	private JTextArea area(Color color)
	{
		JTextArea area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setFont(area.getFont().deriveFont(Font.BOLD).deriveFont((float) 15));
		area.setBackground(BACKGROUND);
		area.setForeground(color);
		area.setEditable(false);
		area.setText("Bonjour !");
		return area;
	}
	
	
	private JLabel label(Icon icon, String text, Color background)
	{
		JLabel label = new JLabel(text);
		label.setIcon(icon);
		label.setOpaque(true);
		label.setBackground(background);
		return label;
	}
}
