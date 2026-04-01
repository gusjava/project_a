package a.entity.gus06.io.transfer.th.toprintstream;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180221";}


	private Service transfer;
	private Service wrapPO;
	private Service startTh;
	
	public EntityImpl() throws Exception
	{
		transfer = Outside.service(this,"gus06.io.transfer.toprintstream");
		wrapPO = Outside.service(this,"gus06.feature.wrap.po.e");
		startTh = Outside.service(this,"gus06.thread.start");
	}
	
	public void p(Object obj) throws Exception
	{
		E exe = (E) wrapPO.t(new Object[]{transfer,obj});
		startTh.p(exe);
	}
	
	public Object t(Object obj) throws Exception
	{
		E exe = (E) wrapPO.t(new Object[]{transfer,obj});
		return startTh.t(exe);
	}
}
