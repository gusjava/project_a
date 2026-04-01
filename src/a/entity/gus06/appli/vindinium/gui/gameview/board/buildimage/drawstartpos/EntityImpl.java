package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage.drawstartpos;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}

	public static final int GAP = 100;
	public static final AlphaComposite AC = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.15F);
	
	
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
		
		
		Composite ac0 = g.getComposite();
		g.setComposite(AC);
		
		int[][] start = (int[][]) data.get(DATA_H_._H_START);
		for(int i=0;i<start.length;i++)
		drawStartPos(g,i+1,start[i]);
		
		g.setComposite(ac0);
	}

	
	private void drawStartPos(Graphics2D g, int id, int[] pos) throws Exception
	{
		Icon icon = findTileIcon(id);
		if(icon!=null) icon.paintIcon(null,g,pos[1]*GAP,pos[0]*GAP);
	}
	
	
	private Icon findTileIcon(int value) throws Exception
	{return (Icon) ip.t("GAME_vindinium_tile"+value);}
}
