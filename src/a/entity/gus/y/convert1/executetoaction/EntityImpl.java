package a.entity.gus.y.convert1.executetoaction;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240110";}

	public Object t(Object obj) throws Exception {
		return new Action1((E) obj);
	}

	private class Action1 extends AbstractAction implements Runnable {
		private E ex;

		public Action1(E ex) {
			this.ex = ex;
		}

		public void run() {
			execute(ex);
		}

		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(this);
		}
	}

	private void execute(E ex) {
		try {
			ex.e();
		} catch (Exception e) {
			Outside.err(this, "execute(E)", e);
		}
	}
}