package a.entity.gus06.string.transform.normalize.filename;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140909";}


	public static final char C = '_';

	/*
	 * caract�res interdits dans les noms de fichier sous Windows :
	 * \ / : * ? " < > |
	 */
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String s = (String) obj;
		
		return s.replace('\\',C).
				replace('/',C).
				replace(':',C).
				replace('*',C).
				replace('?',C).
				replace('"',C).
				replace('<',C).
				replace('>',C).
				replace('|',C).
				replace('\t',C).
				replace('\n',C).
				replace('\r',C);
	}
}
