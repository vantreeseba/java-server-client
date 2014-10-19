import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
	private Socket socket = null;

	public ServerThread(Socket socket) {
		super("ServerThread");
		this.socket = socket;
	}

	public void run() {
		try {
			ObjectInputStream ois = 
				new ObjectInputStream(socket.getInputStream());
			Message input = null;

			while (true) {
				try {
					input = (Message) ois.readObject();
				} catch (Exception ex){
					ex.printStackTrace();
					break;
				}

				if (input != null) {
					if(input.MessageType.equals("LOGIN")){
						try{
							User usr = (User)(input.MessageData);
							System.out.println(usr.username + usr.password);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					break;
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("ERROR: Cannot close socket");
				e.printStackTrace();
			}
		}
	}
}
