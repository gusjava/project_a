package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage.drawpath;

import java.awt.Graphics2D;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	private Service drawPath;
	
	public EntityImpl() throws Exception
	{drawPath = Outside.service(this,"gus06.appli.vindinium.gui.tool.drawpath");}

	

	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		Map data = (Map) t[1];
		
		int[][] path = (int[][]) data.get(DATA_BOT_._BOT_PATH);
		drawPath.p(new Object[]{g,path});
	}
}
