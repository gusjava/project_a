package a.entity.gus06.file.creationtime.date;

import a.framework.*;
import java.io.File;
import java.util.Date;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.Files;
import java.nio.file.Path;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250903";}

	private Service findPath;
	
	public EntityImpl() throws Exception
	{
		findPath = Outside.service(this,"gus06.find.path");
	}
	
	public Object t(Object obj) throws Exception
	{
		Path path = (Path) findPath.t(obj);
		BasicFileAttributes attr = Files.readAttributes(path,BasicFileAttributes.class);
		FileTime time = attr.creationTime();
		return new Date(time.toMillis());
	}
}