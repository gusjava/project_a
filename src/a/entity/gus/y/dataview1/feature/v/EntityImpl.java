package a.entity.gus.y.dataview1.feature.v;

import javax.swing.JLabel;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20231129";}

	private JLabel label;

	private V data;

	public EntityImpl() throws Exception {
		label = new JLabel("PENDING ...");
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (V) obj;
	}

	public Object i() throws Exception {
		return label;
	}
}
