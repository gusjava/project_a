package a.entity.gus.z.server1.engine;

import java.net.ServerSocket;
import java.net.Socket;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.S1;

public class EntityImpl extends S1 implements Entity, P, G, Runnable {
	public String creationDate() {return "20260405";}

	private static final int PORT = 4000;

	private ServerSocket serverSocket;
	private StringBuilder log = new StringBuilder();

	public EntityImpl() throws Exception {
		serverSocket = new ServerSocket(PORT);
		log("Server started on port " + PORT);

		Thread t = new Thread(this, "THREAD_" + getClass().getName());
		t.setDaemon(true);
		t.start();
	}

	public void run() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				log("Connection accepted: " + socket.getRemoteSocketAddress());
				handleSocket(socket);
			}
		}
		catch (Exception e) {
			Outside.err(this, "run()", e);
		}
	}

	public void p(Object obj) throws Exception {
		log((String) obj);
	}

	public Object g() throws Exception {
		return log.toString();
	}

	private void handleSocket(Socket socket) {
		// TODO : déléguer à une entité handle.socket
	}

	private void log(String message) {
		log.append(message).append("\n");
		send(this, "received()");
	}
}
