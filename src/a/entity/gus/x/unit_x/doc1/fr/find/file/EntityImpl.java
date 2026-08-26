package a.entity.gus.x.unit_x.doc1.fr.find.file;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260826";}
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		File rootDir = (File) o[0];
		String unitName = (String) o[1];

		if (unitName == null)
			return null;

		String[] n = unitName.split("\\.", 2);
		if (n.length != 2)
			return null;
		
		String pseudo = n[0];
		String xPart = n[1];

		File xDir = new File(rootDir, "a/config/"+pseudo+"/doc1/fr/src/entity/x");
		return new File(xDir, xPart + ".txt");
	}
}
