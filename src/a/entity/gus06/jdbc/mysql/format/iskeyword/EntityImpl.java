package a.entity.gus06.jdbc.mysql.format.iskeyword;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150624";}

	public boolean f(Object obj) throws Exception
	{
		String name = (String) obj;
		return MYSQL_RESERVED_WORDS.isReservedWord(name);
	}
}