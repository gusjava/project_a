package a.entity.gus06.java.jdk.versionmapping;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}
	
	
	public Object t(Object obj) throws Exception
	{
		int majorVersion = Integer.parseInt(""+obj);
    	
		switch(majorVersion) {
			case 70:return "J2SE 26";
			case 69:return "J2SE 25";
			case 68:return "J2SE 24";
			case 67:return "J2SE 23";
			case 66:return "J2SE 22";
			case 65:return "J2SE 21";
			case 64:return "J2SE 20";
			case 63:return "J2SE 19";
			case 62:return "J2SE 18";
			case 61:return "J2SE 17";
			case 60:return "J2SE 16";
			case 59:return "J2SE 15";
			case 58:return "J2SE 14";
			case 57:return "J2SE 13";
			case 56:return "J2SE 12";
			case 55:return "J2SE 11";
			case 54:return "J2SE 10";
			case 53:return "J2SE 9";
			case 52:return "J2SE 8";
			case 51:return "J2SE 7";
			case 50:return "J2SE 6";
			case 49:return "J2SE 5";
			case 48:return "JDK 1.4";
			case 47:return "JDK 1.3";
			case 46:return "JDK 1.2";
			case 45:return "JDK 1.1";
			default:throw new Exception("Unsupported major version: "+majorVersion);
		}
	}
}