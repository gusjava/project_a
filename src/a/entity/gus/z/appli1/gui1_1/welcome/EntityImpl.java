package a.entity.gus.z.appli1.gui1_1.welcome;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231231";}
	
	public static final String PATH = "/a/config/gus/doc1/fr/app/appli1/doc_user.txt";

	private Service docView;
	
	public EntityImpl() throws Exception {
		docView = Outside.service(this, "*gus.y.docview1.gui1a.first");
		docView.p(PATH);
	}
	
	public Object i() throws Exception {
		return docView.i();
	}
}
