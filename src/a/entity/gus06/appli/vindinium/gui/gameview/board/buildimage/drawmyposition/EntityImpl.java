package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage.drawmyposition;

import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	public static final int GAP = 100;
	public static final String ICONID = "GAME_vindinium_me";
	
	
	private Service ip;

	public EntityImpl() throws Exception
	{
		ip = Outside.service(this,"gus06.icon.provider");
	}

	

	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		Map data = (Map) t[1];
		
		int[] pos = (int[]) data.get(DATA_ME_._ME_POS);
		drawMyPosition(g,pos[1],pos[0]);
	}


	
	
	private void drawMyPosition(Graphics2D g, int i, int j) throws Exception
	{
		Icon icon = (Icon) ip.t(ICONID);
		if(icon==null) return;
		
		int x0 = (GAP-icon.getIconWidth())/2;
		int y0 = GAP-icon.getIconHeight();
		if(icon!=null) icon.paintIcon(null,g,i*GAP+x0,j*GAP+y0);
	}
}
