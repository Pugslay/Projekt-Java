import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private static Socket clientSocket1 = null;
    private static Socket clientSocket2 = null;
    private static  ServerSocket serverSocket=null;
    private static Server thread1=null;
	private static Server thread2=null;
	public static void main(String[] args){
		try {
			serverSocket = new ServerSocket(6868);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			if(thread1==null||thread2==null) {
				System.out.println("null");
			}
		if (clientSocket1 == null) {
	        try {
				clientSocket1 = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Client connected: " + clientSocket1.getInetAddress().getHostAddress());
	    }
		if(clientSocket2 == null){
	        try {
				clientSocket2 = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Client connected: " + clientSocket2.getInetAddress().getHostAddress());
	    }
	    if (thread1 == null&& clientSocket2 != null&&clientSocket1 != null) {
	        System.out.println("Thread 1  start");
	        thread1=new Server();
	        thread1.start();
	    }
	    if (thread2 == null&& clientSocket2 != null&&clientSocket1 != null) {
	        System.out.println("Thread 2  start");
	        thread2=new Server();
	        thread2.start();
	    }
		}
	}
	@Override
	public void run() {
		if(this==thread1) {
			boolean stay=true;
			do {
			InputStream input = null;
			String text=null;
			String str=null;
				while(text==null) {
					try {
						input = clientSocket1.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BufferedReader reader = new BufferedReader(new InputStreamReader(input));
					try {
						str = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						text = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	przekierowanie(str, text, clientSocket1, clientSocket2);
			}while(stay);
			System.out.println("out");
		}
		if(this==thread2) {
			boolean stay=true;
			do {
			InputStream input = null;
			String text=null;
			String str=null;
				while(text==null) {
					try {
						input = clientSocket2.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BufferedReader reader = new BufferedReader(new InputStreamReader(input));
					try {
						str = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						text = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	przekierowanie(str, text, clientSocket2, clientSocket1);
			}while(stay);
			System.out.println("out");
			}
		}
	private void przekierowanie(String id,String message,Socket Player,Socket Oponent) {
		if(id=="set") {
			SET(message);
		}
		if(id=="send") {
			OutputStream output = null;
			try {
				output = Oponent.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter writer = new PrintWriter(output, true);
			writer.println(message);
			message=null;
		}
		if(id=="get") {
			OutputStream output = null;
			try {
				output = Player.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter writer = new PrintWriter(output, true);
			if(this==thread1) {
				writer.println("Conected as first");
				message=null;
			}
			if(this==thread2) {
				writer.println("Conected as second");
				message=null;
			}
		}
		id=null;
		message=null;
	}
	public void SET(String message) {
		System.out.println(message);
		//
	}
}
