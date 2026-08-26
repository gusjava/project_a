package a.entity.gus.x.entity.doc1.fr.find.file;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240118";}
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		File rootDir = (File) o[0];
		String entityName = (String) o[1];

		if (entityName == null)
			return null;

		String[] n = entityName.split("\\.");
		if (n.length < 3)
			return null;
		
		String pseudo = n[0];

		File docDir = new File(rootDir, "a/config/"+pseudo+"/doc1/fr/src/entity");

		String type = n[1];
		if (type.equals("x"))
			return docFileForX(docDir, entityName);
		if (type.equals("y"))
			return docFileForY(docDir, n);
		if (type.equals("z"))
			return docFileForZ(docDir, n);

		return null;
	}

	private File docFileForX(File docDir, String entityName) {
		String[] n = entityName.split("\\.x\\.", 2);
		File xDir = new File(docDir, "x");
		return new File(xDir, n[1] + ".txt");
	}

	private File docFileForY(File docDir, String[] n) {
		File yDir = new File(docDir, "y");
		return new File(yDir, n[2] + ".txt");
	}

	private File docFileForZ(File docDir, String[] n) {
		File zDir = new File(docDir, "z");
		return new File(zDir, n[2] + ".txt");
	}
}
