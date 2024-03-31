package a.core.gus.gyem.m033.t.read.icon;

import java.net.URL;

import javax.swing.ImageIcon;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String path = (String) obj;
		if(path==null) return null;
		URL url = getClass().getResource(path);
		if(url==null) return null;
		return new ImageIcon(url);
	}
}
