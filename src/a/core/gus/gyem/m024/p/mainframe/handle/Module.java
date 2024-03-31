package a.core.gus.gyem.m024.p.mainframe.handle;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import a.core.gus.gyem.GyemSystem;
import a.framework.P;

public class Module extends GyemSystem implements P {

	public void p(Object obj) throws Exception {
		JFrame frame = (JFrame) obj;
		
		Container content = (Container) moduleI(M025_I_MAINFRAME_CONTENT).i();
		frame.setContentPane(content);
		
		Map prop = (Map) moduleG(M003_G_PROP).g();
		
		String title = prop(prop, PROP_APP_TITLE, DEFAULTPROP_APP_TITLE);
		String size = prop(prop, PROP_APP_SIZE, DEFAULTPROP_APP_SIZE);
		String iconId = prop(prop, PROP_APP_ICON, null);
		
		frame.setTitle(title);
		frame.setSize(dim(size));
		frame.setIconImage(toImage(iconId));
		
		frame.setLocationRelativeTo(null);
		log(this, "Displaying main frame...");
		frame.setVisible(true);
		log(this, "Main frame displayed");
	}
	
	
	
	private String prop(Map prop, String key, String defaultValue) {
		if(!prop.containsKey(key)) return defaultValue;
		return (String) prop.get(key);
	}
	
	private Dimension dim(String size) {
		String[] n = size.split(" ");
		return new Dimension(i_(n[0]),i_(n[1]));
	}
	
	private int i_(String s){
		return Integer.parseInt(s);
	}
	
	private Image toImage(String iconId) throws Exception {
		ImageIcon icon = (ImageIcon) moduleT(M034_T_CONFIG_ICON).t(iconId);
		return icon!=null ? icon.getImage() : null;
	}
}
