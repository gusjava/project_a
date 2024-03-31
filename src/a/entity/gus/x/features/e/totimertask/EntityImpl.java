package a.entity.gus.x.features.e.totimertask;

import java.util.TimerTask;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	public Object t(Object obj) throws Exception {
		return new TimerTask1((E) obj);
	}

	private class TimerTask1 extends TimerTask {
		private E exe;

		public TimerTask1(E exe) {
			super();
			this.exe = exe;
		}

		public void run() {
			execute(exe);
		}
	}

	private void execute(E exe) {
		try {
			exe.e();
		} catch (Exception e) {
			Outside.err(this, "execute(E)", e);
		}
	}
}
