package a.entity.gus06.sys.desktop1.gui.pane.cust;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.Color;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191121";}
	
	public static final String KEY_PROP = "prop";
	public static final String KEY_DIR_IMAGE = "dir_image";
	public static final String KEY_GUI_PANE = "gui_pane";
	
	public static final String KEY_BG_IMAGE = "background.image";
	public static final String KEY_BG_COLOR = "background.color";
	public static final String KEY_BG_MODE = "background.mode";

	private Service readImage;
	private Service findColor;
	
	
	
	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.imageio");
		findColor = Outside.service(this,"gus06.convert.stringtocolor");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map main = (Map) obj;
		
		JComponent pane = (JComponent) main.get(KEY_GUI_PANE);
		
		Map prop = (Map) main.get(KEY_PROP);
		File imageDir = (File) main.get(KEY_DIR_IMAGE);
		
		String bgImageS = getProp(prop,KEY_BG_IMAGE);
		String bgMode = getProp(prop,KEY_BG_MODE);
		String bgColorS = getProp(prop,KEY_BG_COLOR);
		
		if(bgImageS!=null)
		{
			File bgImageFile = new File(imageDir,bgImageS);
			if(bgImageFile.isFile())
			{
				Object bgImage = readImage.t(bgImageFile);
				((V)pane).v(bgMode,bgImage);
			}
		}
		if(bgColorS!=null)
		{
			Color bgColor = (Color) findColor.t(bgColorS);
			pane.setBackground(bgColor);
		}
	}
	
	
	
	private String getProp(Map prop, String key)
	{
		if(!prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
}
