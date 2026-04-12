package a.entity.gus.y.dataview1.feature.r;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20231129";}

	private Service shiftPanel;
	private Service gui1;
	private Service gui2;

	private R data;

	public EntityImpl() throws Exception {
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		gui1 = Outside.service(this, "*gus.y.dataview1.feature.r.gui1");
		gui2 = Outside.service(this, "*gus.y.dataview1.feature.r.gui2");
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (R) obj;
		if(data==null) {
			gui1.p(null);
			gui2.p(null);
			shiftPanel.p(null);
			return;
		}
		
		try {
			data.r("keys");
			gui1.p(null);
			gui2.p(data);
			shiftPanel.p(gui1);
		} catch(Exception e) {
			gui1.p(data);
			gui2.p(null);
			shiftPanel.p(gui1);
		}
	}

	public Object i() throws Exception {
		return shiftPanel.i();
	}

}
