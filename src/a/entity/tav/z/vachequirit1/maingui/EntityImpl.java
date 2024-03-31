package a.entity.tav.z.vachequirit1.maingui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20240104";}

	private Service screen;
	private Service capture;
	private Service timer;
	
	public EntityImpl() throws Exception {
		screen = Outside.service(this,"*gus.x.swing.panel.imagepanel.fit");
		capture = Outside.service(this,"gus.y.screen1.printscreen");
		timer = Outside.service(this,"gus.y.timer2.send.ms100");
		
		timer.addActionListener(this);
		
	}

	public Object i() throws Exception {
		return screen.i();
	}

	public void actionPerformed(ActionEvent e) {
		refresh();
	}
	
	private void refresh() {
		try {
			Object img = capture.g();
			screen.p(img);
		} catch (Exception e) {
			Outside.err(this, "refresh()", e);
		}
	}
}
