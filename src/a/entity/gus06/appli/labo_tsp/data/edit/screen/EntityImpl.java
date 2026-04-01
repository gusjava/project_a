package a.entity.gus06.appli.labo_tsp.data.edit.screen;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20190307";}

	public static final int POINTSIZE = 1;
	public static final int CROSSLENGTH = 5;
	public static final Color POINTCOLOR = Color.WHITE;
	public static final Color BACKGROUND = Color.BLACK;
	

	private Service manager;
	private Service screen;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.labo_tsp.data.manager");
		screen = Outside.service(this,"*gus06.swing.panel.screen.points");
		
		screen.v("pointsize",""+POINTSIZE);
		screen.v("crosslength",""+CROSSLENGTH);
		screen.v("color",BACKGROUND);
		
		manager.addActionListener(this);
		refresh();
	}
	
	
	public Object i() throws Exception
	{return screen.i();}


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	private void refresh()
	{
		try
		{
			int[] dim = (int[]) manager.r("dim");
			List towns = (List) manager.r("towns");
			
			Map map = new HashMap();
			for(int i=0;i<towns.size();i++)
			{
				double[] town = (double[]) towns.get(i);
				map.put(town,POINTCOLOR);
			}
			
			screen.v("dim",dim);
			screen.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}

}
