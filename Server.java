import java.net.ServerSocket;

public class Server {

	public static void main(String[] args){
		System.out.println("Server Starting");

		Server foo = new Server(40000);
	}

	public Server(int port){
		System.out.println("Initializing server.");
		
		try{
			ServerSocket serverSocket = new ServerSocket(port);

			while(true){
				new ServerThread(serverSocket.accept()).start();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ServerSocket socket;
}
