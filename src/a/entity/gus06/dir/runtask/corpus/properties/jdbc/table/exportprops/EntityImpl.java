package a.entity.gus06.dir.runtask.corpus.properties.jdbc.table.exportprops;

import a.framework.*;
import java.io.File;
import javax.mail.Folder;
import javax.mail.Message;
import java.util.Properties;
import java.util.Set;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161114";}


	private Service askInfos;
	private Service buildCx;
	private Service selectTablePath;
	private Service insertRow;
	private Service buildListing;
	private Service readProp;
	
	public EntityImpl() throws Exception
	{
		askInfos = Outside.service(this,"gus06.security.askinfo.urlloginpassword1");
		buildCx = Outside.service(this,"gus06.jdbc.connection.builder");
		selectTablePath = Outside.service(this,"gus06.jdbc.gui.selector.path.dialog1");
		insertRow = Outside.service(this,"gus06.jdbc.mysql.perform.row.insert");
		buildListing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readProp = Outside.service(this,"gus06.file.read.properties");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String[] infos = (String[]) askInfos.g();
		if(infos==null) return;
		
		Connection cx = (Connection) buildCx.t(infos);
		String tablePath = (String) selectTablePath.t(cx);
		if(tablePath==null) return;
		
		File[] ff = (File[]) buildListing.t(dir);
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(int i=0;i<size;i++)
		{
			Map prop = (Map) readProp.t(ff[i]);
			insertRow.p(new Object[]{cx,tablePath,prop});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		cx.close();
	}
}
