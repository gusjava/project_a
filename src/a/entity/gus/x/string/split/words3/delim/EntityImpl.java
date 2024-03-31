package a.entity.gus.x.string.split.words3.delim;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240120";}

	public static String DELIM = "\"`'Ф\\ \n\r\t/[](){}<>|!?:,;=-+*%@#&$згд^и";

	public Object g() throws Exception {
		return DELIM;
	}
}