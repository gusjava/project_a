package a.entity.gus.y.appview1.entryview.panel.gif;

import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20231128";}

	private Service isToByteArray;
	private JLabel label;

	public EntityImpl() throws Exception {
		isToByteArray = Outside.service(this, "gus.x.io.build.bytearray");
		label = new JLabel(" ");
	}

	public Object i() throws Exception {
		return label;
	}

	public void p(Object obj) throws Exception {
		InputStream is = (InputStream) obj;
		byte[] data = (byte[]) isToByteArray.t(is);
		label.setIcon(new ImageIcon(data));
	}
}