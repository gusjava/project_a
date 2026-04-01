package a.entity.gus06.jdbc.postgresql.format.iskeyword;

public class POSTGRESQL_RESERVED_WORDS {

	public static String ABORT = "ABORT";
	public static String ABSENT = "ABSENT";
	public static String ACCESS = "ACCESS";
	public static String ACTION = "ACTION";
	public static String ADD = "ADD";
	public static String ALL = "ALL";
	public static String ALTER = "ALTER";
	public static String ANALYSE = "ANALYSE";
	public static String ANALYZE = "ANALYZE";
	public static String AND = "AND";
	public static String ANY = "ANY";
	public static String ARRAY = "ARRAY";
	public static String AS = "AS";
	public static String ASC = "ASC";
	public static String ASYMMETRIC = "ASYMMETRIC";
	public static String AUTHORIZATION = "AUTHORIZATION";
	public static String BACKWARD = "BACKWARD";
	public static String BEFORE = "BEFORE";
	public static String BEGIN = "BEGIN";
	public static String BETWEEN = "BETWEEN";
	public static String BIGINT = "BIGINT";
	public static String BINARY = "BINARY";
	public static String BIT = "BIT";
	public static String BOOLEAN = "BOOLEAN";
	public static String BOTH = "BOTH";
	public static String BY = "BY";
	public static String CASE = "CASE";
	public static String CAST = "CAST";
	public static String CHECK = "CHECK";
	public static String COLLATE = "COLLATE";
	public static String COLUMN = "COLUMN";
	public static String CONCURRENTLY = "CONCURRENTLY";
	public static String CONSTRAINT = "CONSTRAINT";
	public static String CREATE = "CREATE";
	public static String CROSS = "CROSS";
	public static String CURRENT_DATE = "CURRENT_DATE";
	public static String CURRENT_ROLE = "CURRENT_ROLE";
	public static String CURRENT_TIME = "CURRENT_TIME";
	public static String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
	public static String CURRENT_USER = "CURRENT_USER";
	public static String DEFAULT = "DEFAULT";
	public static String DEFERRABLE = "DEFERRABLE";
	public static String DESC = "DESC";
	public static String DISTINCT = "DISTINCT";
	public static String DO = "DO";
	public static String ELSE = "ELSE";
	public static String END = "END";
	public static String EXCEPT = "EXCEPT";
	public static String EXISTS = "EXISTS";
	public static String EXTRACT = "EXTRACT";
	public static String FALSE = "FALSE";
	public static String FETCH = "FETCH";
	public static String FOR = "FOR";
	public static String FOREIGN = "FOREIGN";
	public static String FROM = "FROM";
	public static String FULL = "FULL";
	public static String GRANT = "GRANT";
	public static String GROUP = "GROUP";
	public static String HAVING = "HAVING";
	public static String IN = "IN";
	public static String INITIALLY = "INITIALLY";
	public static String INNER = "INNER";
	public static String INSERT = "INSERT";
	public static String INTERSECT = "INTERSECT";
	public static String INTO = "INTO";
	public static String IS = "IS";
	public static String JOIN = "JOIN";
	public static String LATERAL = "LATERAL";
	public static String LEADING = "LEADING";
	public static String LEFT = "LEFT";
	public static String LIKE = "LIKE";
	public static String LIMIT = "LIMIT";
	public static String LOCALTIME = "LOCALTIME";
	public static String LOCALTIMESTAMP = "LOCALTIMESTAMP";
	public static String NATURAL = "NATURAL";
	public static String NOT = "NOT";
	public static String NULL = "NULL";
	public static String OFFSET = "OFFSET";
	public static String ON = "ON";
	public static String ONLY = "ONLY";
	public static String OR = "OR";
	public static String ORDER = "ORDER";
	public static String OUTER = "OUTER";
	public static String OVERLAPS = "OVERLAPS";
	public static String PLACING = "PLACING";
	public static String PRIMARY = "PRIMARY";
	public static String REFERENCES = "REFERENCES";
	public static String RETURNING = "RETURNING";
	public static String RIGHT = "RIGHT";
	public static String SELECT = "SELECT";
	public static String SESSION_USER = "SESSION_USER";
	public static String SIMILAR = "SIMILAR";
	public static String SOME = "SOME";
	public static String SYMMETRIC = "SYMMETRIC";
	public static String TABLE = "TABLE";
	public static String THEN = "THEN";
	public static String TO = "TO";
	public static String TRAILING = "TRAILING";
	public static String TRUE = "TRUE";
	public static String UNION = "UNION";
	public static String UNIQUE = "UNIQUE";
	public static String USER = "USER";
	public static String USING = "USING";
	public static String VARIADIC = "VARIADIC";
	public static String VERBOSE = "VERBOSE";
	public static String WHEN = "WHEN";
	public static String WHERE = "WHERE";
	public static String WINDOW = "WINDOW";
	public static String WITH = "WITH";

	public static boolean isReservedWord(String word) throws Exception
	{
		try{POSTGRESQL_RESERVED_WORDS.class.getField(word.toUpperCase());}
		catch (SecurityException e)
		{throw new Exception("A security exception occurred inside " + POSTGRESQL_RESERVED_WORDS.class, e);}
		catch (NoSuchFieldException e) {return false;}
		return true;
	}
}
