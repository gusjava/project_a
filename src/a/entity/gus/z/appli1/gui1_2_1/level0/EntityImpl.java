package a.entity.gus.z.appli1.gui1_2_1.level0;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231231";}
	
	public static final String PATH = "/a/config/gus/doc0/fr/levels/a_fr_level0.txt";

	private Service docView;
	private Icon icon;
	
	public EntityImpl() throws Exception {
		docView = Outside.service(this, "*gus.y.docview1.gui1a");
		icon = (Icon) Outside.resource(this, "icon#ELEMENT_a");
		
		docView.v("icon", icon);
		docView.p(PATH);
	}
	
	public Object i() throws Exception {
		return docView.i();
	}
}
