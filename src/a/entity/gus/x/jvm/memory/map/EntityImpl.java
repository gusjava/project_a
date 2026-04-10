package a.entity.gus.x.jvm.memory.map;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20260410";}

	public Object g() throws Exception
	{
		Runtime rt = Runtime.getRuntime();
		long total = rt.totalMemory();
		long free = rt.freeMemory();
		long used = total - free;
		long max = rt.maxMemory();

		MemoryMXBean mx = ManagementFactory.getMemoryMXBean();
		long nonHeapUsed = mx.getNonHeapMemoryUsage().getUsed();
		long nonHeapMax = mx.getNonHeapMemoryUsage().getMax();

		Map map = new HashMap();
		map.put("heap.used", String.valueOf(used));
		map.put("heap.total", String.valueOf(total));
		map.put("heap.max", String.valueOf(max));
		map.put("heap.free", String.valueOf(free));
		map.put("nonheap.used", String.valueOf(nonHeapUsed));
		map.put("nonheap.max", String.valueOf(nonHeapMax));
		return map;
	}
}
