package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage.drawtmine1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}

	public static final int GAP = 100;
	public static final Color COLOR = Color.LIGHT_GRAY;
	
	
	private Service tile1Tmine;

	public EntityImpl() throws Exception
	{
		tile1Tmine = Outside.service(this,"gus06.appli.vindinium.bot.tool.tile1finder.tmine");
	}



	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		Map data = (Map) t[1];
		
		
		g.setColor(COLOR);
		
		List tile1 = (List) tile1Tmine.t(data);
		for(int i=0;i<tile1.size();i++)
		{
			int[] pos = (int[]) tile1.get(i);
			fillRect(g,pos[1],pos[0]);
		}
	}


	
	
	private void fillRect(Graphics2D g, int i, int j)
	{
		g.fillRect(i*GAP,j*GAP,GAP,GAP);
	}
}
