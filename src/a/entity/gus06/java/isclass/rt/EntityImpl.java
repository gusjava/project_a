package a.entity.gus06.java.isclass.rt;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170223";}
	
	public static final String[] STARTS = new String[]{
		"java.applet","java.awt","java.beans","java.io",
		"java.lang","java.math","java.net","java.nio","java.rmi",
		"java.security","java.sql","java.text","java.time","java.util",
		"javax.accessibility","javax.activation","javax.activity",
		"javax.annotation","javax.imageio","javax.jws","javax.lang",
		"javax.management","javax.naming","javax.net","javax.print",
		"javax.rmi","javax.script","javax.security","javax.smartcardio",
		"javax.sound","javax.sql","javax.swing","javax.tools",
		"javax.transaction","javax.xml",
		"org.ietf","org.jcp","org.omg","org.w3c","org.xml"};
	
	
	public boolean f(Object obj) throws Exception
	{
		String classpath = (String) obj;
		for(String n:STARTS) if(classpath.equals(n) || classpath.startsWith(n+".")) return true;
		return false;
	}
}