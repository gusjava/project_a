package a.entity.gus.z.appli1.bottombar.devlabel;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231203";}

	private Service findDev;
	private Icon devIcon;

	private JLabel label;

	public EntityImpl() throws Exception {
		findDev = Outside.service(this, "gus.y.srcroot1.dev");
		devIcon = (Icon) Outside.resource(this, "icon#ELEMENT_dev");

		label = new JLabel();
		label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		String devId = (String) findDev.g();
		if(devId!=null) {
			label.setText(devId);
			label.setIcon(devIcon);
		}
	}

	public Object i() throws Exception {
		return label;
	}
}
