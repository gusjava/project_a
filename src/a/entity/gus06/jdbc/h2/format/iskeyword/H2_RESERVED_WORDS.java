package a.entity.gus06.jdbc.h2.format.iskeyword;

public class H2_RESERVED_WORDS {
	
	public static String ADD = "ADD";
	public static String ALL = "ALL";
	public static String ALTER = "ALTER";
	public static String AS = "AS";
	public static String ASC = "ASC";
	public static String BETWEEN = "BETWEEN";
	public static String CASE = "CASE";
	public static String CAST = "CAST";
	public static String CHECK = "CHECK";
	public static String COLUMN = "COLUMN";
	public static String COMMIT = "COMMIT";
	public static String CREATE = "CREATE";
	public static String DELETE = "DELETE";
	public static String DISTINCT = "DISTINCT";
	public static String DROP = "DROP";
	public static String EXISTS = "EXISTS";
	public static String FALSE = "FALSE";
	public static String FOR = "FOR";
	public static String FROM = "FROM";
	public static String GROUP = "GROUP";
	public static String HAVING = "HAVING";
	public static String IN = "IN";
	public static String INSERT = "INSERT";
	public static String INT = "INT";
	public static String INTEGER = "INTEGER";
	public static String IS = "IS";
	public static String LIKE = "LIKE";
	public static String NOT = "NOT";
	public static String NULL = "NULL";
	public static String ON = "ON";
	public static String OR = "OR";
	public static String ORDER = "ORDER";
	public static String PRIMARY = "PRIMARY";
	public static String SELECT = "SELECT";
	public static String SET = "SET";
	public static String TABLE = "TABLE";
	public static String TRUE = "TRUE";
	public static String UNION = "UNION";
	public static String UPDATE = "UPDATE";
	public static String VALUES = "VALUES";
	public static String WHERE = "WHERE";
	
	public static boolean isReservedWord(String word) throws Exception
	{
		try{H2_RESERVED_WORDS.class.getField(word.toUpperCase());}
		catch(SecurityException e) {throw new Exception("A security exception occured inside "+H2_RESERVED_WORDS.class,e);}
		catch(NoSuchFieldException e) {return false;}
		return true;
	}
}
