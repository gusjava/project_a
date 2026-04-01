package a.entity.gus06.java.classname.topackage.usual;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170304";}

	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		
		
		if(name.equals("Boolean")) return "java.lang";
		if(name.equals("Byte")) return "java.lang";
		if(name.equals("Character")) return "java.lang";
		if(name.equals("Class")) return "java.lang";
		if(name.equals("ClassLoader")) return "java.lang";
		if(name.equals("Double")) return "java.lang";
		if(name.equals("Float")) return "java.lang";
		if(name.equals("Integer")) return "java.lang";
		if(name.equals("Long")) return "java.lang";
		if(name.equals("Math")) return "java.lang";
		if(name.equals("Number")) return "java.lang";
		if(name.equals("Object")) return "java.lang";
		if(name.equals("Process")) return "java.lang";
		if(name.equals("Runtime")) return "java.lang";
		if(name.equals("String")) return "java.lang";
		if(name.equals("System")) return "java.lang";
		if(name.equals("Thread")) return "java.lang";
		if(name.equals("Short")) return "java.lang";
		
		if(name.equals("BigDecimal")) return "java.math";
		if(name.equals("BigInteger")) return "java.math";
		
		if(name.equals("Date")) return "java.util";
		if(name.equals("Calendar")) return "java.util";
		if(name.equals("Arrays")) return "java.util";
		if(name.equals("Locale")) return "java.util";
		if(name.equals("Timer")) return "java.util";
		if(name.equals("TimerTask")) return "java.util";
		if(name.equals("ResourceBundle")) return "java.util";
		if(name.equals("Map")) return "java.util";
		if(name.equals("HashMap")) return "java.util";
		if(name.equals("Set")) return "java.util";
		if(name.equals("HashSet")) return "java.util";
		if(name.equals("List")) return "java.util";
		if(name.equals("ArrayList")) return "java.util";
		if(name.equals("Collection")) return "java.util";
		if(name.equals("Collections")) return "java.util";
		if(name.equals("Iterator")) return "java.util";
		if(name.equals("Enumeration")) return "java.util";
		if(name.equals("Comparator")) return "java.util";
		if(name.equals("Properties")) return "java.util";
		if(name.equals("Vector")) return "java.util";
		if(name.equals("Random")) return "java.util";
		
		if(name.equals("Matcher")) return "java.util.regex";
		if(name.equals("Pattern")) return "java.util.regex";
		if(name.equals("MatchResult")) return "java.util.regex";
		
		if(name.equals("DateFormat")) return "java.text";
		if(name.equals("SimpleDateFormat")) return "java.text";
		if(name.equals("NumberFormat")) return "java.text";
		if(name.equals("DecimalFormat")) return "java.text";
		
		if(name.equals("File")) return "java.io";
		if(name.equals("InputStream")) return "java.io";
		if(name.equals("OutputStream")) return "java.io";
		if(name.equals("BufferedInputStream")) return "java.io";
		if(name.equals("BufferedOutputStream")) return "java.io";
		if(name.equals("FileInputStream")) return "java.io";
		if(name.equals("FileOutputStream")) return "java.io";
		if(name.equals("DataInputStream")) return "java.io";
		if(name.equals("DataOutputStream")) return "java.io";
		if(name.equals("ObjectInputStream")) return "java.io";
		if(name.equals("ObjectOutputStream")) return "java.io";
		if(name.equals("Reader")) return "java.io";
		if(name.equals("Writer")) return "java.io";
		if(name.equals("FileReader")) return "java.io";
		if(name.equals("FileWriter")) return "java.io";
		
		if(name.equals("Connection")) return "java.sql";
		if(name.equals("ResultSet")) return "java.sql";
		if(name.equals("ResultSetMetaData")) return "java.sql";
		if(name.equals("Statement")) return "java.sql";
		if(name.equals("CallableStatement")) return "java.sql";
		if(name.equals("PreparedStatement")) return "java.sql";
		
		if(name.equals("URI")) return "java.net";
		if(name.equals("URL")) return "java.net";
		if(name.equals("URLClassLoader")) return "java.net";
		if(name.equals("URLDecoder")) return "java.net";
		if(name.equals("URLEncoder")) return "java.net";
		if(name.equals("Socket")) return "java.net";
		if(name.equals("ServerSocket")) return "java.net";
		
		return null;
	}
}