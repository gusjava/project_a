package a.entity.gus06.thread.info.lockinfo;

import a.framework.*;
import java.lang.management.LockInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ManagementFactory;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180124";}
	
	
	public Object t(Object obj) throws Exception
	{
		Thread t = (Thread) obj;
		if(t==null) return "";
		
		ThreadInfo[] tInfos = ManagementFactory.getThreadMXBean().getThreadInfo(new long[]{t.getId()}, true, true);
		if(tInfos==null || tInfos.length==0) return "";
		LockInfo lInfo = tInfos[0].getLockInfo();
		if(lInfo==null) return "";
		
		return lInfo.toString();
	}
}
