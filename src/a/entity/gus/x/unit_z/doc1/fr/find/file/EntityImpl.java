package a.entity.gus.x.unit_z.doc1.fr.find.file;

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

		String[] n = unitName.split("\\.");
		if (n.length != 2)
			throw new Exception("Invalid unit y name: " + unitName);
		
		String pseudo = n[0];
		String zPart = n[1];

		File zDir = new File(rootDir, "a/config/"+pseudo+"/doc1/fr/src/entity/z");
		return new File(zDir, zPart + ".txt");
	}
}
