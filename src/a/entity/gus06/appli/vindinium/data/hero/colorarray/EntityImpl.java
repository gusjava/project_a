package a.entity.gus06.appli.vindinium.data.hero.colorarray;

import java.awt.Color;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170923";}

	public static final Color[] HERO_COLORS = new Color[]{Color.RED,Color.CYAN,Color.GREEN,Color.YELLOW};

	public Object g() throws Exception
	{return HERO_COLORS;}
}
