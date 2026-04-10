package a.entity.gus.x.thread.watchdog;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20260410";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		T treatment = (T) o[0];
		long timeout = (Long) o[1];
		return new WatchdogT(treatment, timeout);
	}

	private static class WatchdogT implements T
	{
		private T treatment;
		private long timeout;

		WatchdogT(T treatment, long timeout)
		{
			this.treatment = treatment;
			this.timeout = timeout;
		}

		public Object t(Object obj) throws Exception
		{
			Thread[] worker = new Thread[1];
			ExecutorService exec = Executors.newSingleThreadExecutor(r ->
			{
				worker[0] = new Thread(r, "watchdog-worker");
				return worker[0];
			});

			Callable task = () -> treatment.t(obj);
			Future future = exec.submit(task);
			exec.shutdown();

			try
			{
				return future.get(timeout, TimeUnit.SECONDS);
			}
			catch(TimeoutException e)
			{
				String msg = formatTimeout(worker[0]);
				future.cancel(true);
				return msg;
			}
			catch(ExecutionException e)
			{
				return formatError(e.getCause());
			}
		}

		private String formatTimeout(Thread thread)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Traitement interrompu après " + timeout + "s\n");
			sb.append("Thread \"" + thread.getName() + "\" bloqué ici :\n");
			StackTraceElement[] stack = thread.getStackTrace();
			for(int i = 0; i < stack.length; i++)
				sb.append("  " + stack[i] + "\n");
			return sb.toString().trim();
		}

		private String formatError(Throwable t)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Erreur : " + t + "\n");
			StackTraceElement[] stack = t.getStackTrace();
			for(int i = 0; i < stack.length; i++)
				sb.append("  " + stack[i] + "\n");
			return sb.toString().trim();
		}
	}
}
