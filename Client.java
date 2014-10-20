import java.net.Socket;
import java.io.*;

public class Client {

	public static void main(String[] args){
		System.out.println("Client Starting");

		Client foo = new Client();
//		foo.SendMessage("WOOOOTY");
		foo.Login("test","pass");
		foo.Close();
	}

	private Socket socket;
	private PrintWriter out;
	private ObjectOutputStream oout;
	private ObjectInputStream ois;

	public Client(){
		System.out.println("Initializing Client.");
		init();
	}

	private void init(){
		try {
			socket = new Socket("127.0.0.1",40000);
//			out = new PrintWriter(socket.getOutputStream(), true);
			oout = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Login(String username, String pass){
		User testUser = new User();
		testUser.username = username;
		testUser.password = pass;

		SendMessage(testUser, "LOGIN");
	}

	public void SendMessage(Object obj, String messageType){
		Message msg = new Message();
		msg.MessageType = messageType;
		msg.MessageData = obj;

		try{
			oout.writeObject(msg);
			oout.flush();

			
			Object input = ois.readObject();
			System.out.println("got this: " + input);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void SendMessage(String message){
		out.println(message);
		out.flush();
	}

	public void Close(){
		try {
//			out.close();
			socket.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
