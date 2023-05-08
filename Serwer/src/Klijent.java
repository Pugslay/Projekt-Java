import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
public class Klijent extends Thread {
	private List<String>lista=new ArrayList<>();
	private Socket socket;
	private Klijent thread1=null;
	boolean close;
	
	public Klijent() {
		// TODO Auto-generated constructor stub
		close=false;
		do {
		try {
			socket = new Socket("localhost", 6868);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (thread1 == null&& socket!=null) {
	        thread1=new Klijent();
	        thread1.start();
	    }
		}while(socket==null||thread1==null);
	}
	
	public String SendToOponent(String str) {
		OutputStream output = null;
		try {
			output = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("send");
        writer.println(str);
		if(lista.isEmpty()) {
			return null;
		}else {
			return lista.remove(0);
		}
	}
	
	public String SendToServer(String str) {
		OutputStream output = null;
		try {
			output = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("set");
        writer.println(str);
		if(lista.isEmpty()) {
			return null;
		}else {
			return lista.remove(0);
		}
	}
	
	public String LogIn(String str) {
		OutputStream output = null;
		try {
			output = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("get");
        writer.println(str);
		if(lista.isEmpty()) {
			return null;
		}else {
			return lista.remove(0);
		}
	}
	
	public void Close() {
		close=true;
		if(!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		thread1.Close();
	}
	
	@Override
	public void run() {
		InputStream input = null;
		String str=null;
		do {
			try {
				input = socket.getInputStream();
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
			if(str!=null) {
				lista.add(str);
			}
		}while(socket!=null&&!close);
		if(!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
