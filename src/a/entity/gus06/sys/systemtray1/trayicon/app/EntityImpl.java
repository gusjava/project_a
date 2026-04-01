package a.entity.gus06.sys.systemtray1.trayicon.app;

import a.framework.*;
import java.awt.TrayIcon;
import javax.swing.Icon;
import java.awt.Image;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160421";}


	private Service iconToImage;
	private Service getAppIcon;
	
	private String appTitle;
	private TrayIcon tray;
	

	public EntityImpl() throws Exception
	{
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
		getAppIcon = Outside.service(this,"gus06.app.icon");
		appTitle = (String) Outside.resource(this,"property#app.title");
	}
	
	
	public Object g() throws Exception
	{
		if(tray==null) init();
		return tray;
	}
	
	
	private void init() throws Exception
	{
		Icon icon = (Icon) getAppIcon.g();
		if(icon==null) throw new Exception("App icon not found");
		
		Image image = (Image) iconToImage.t(icon);
		tray = new TrayIcon(image,appTitle);
	}
}
