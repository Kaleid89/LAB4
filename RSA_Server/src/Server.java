import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] arr)    {
		String message;
		int port = 4567;
		byte[] data = null;
		try {
			ServerSocket ss = new ServerSocket(port); 
			System.out.println("Waiting for a client...");
			Socket socket = ss.accept(); 
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);
			data = new byte[in.readInt()];
			in.read(data);
			RSA_Server.set_exp(data);
			data = new byte[in.readInt()];
			in.read(data);
			RSA_Server.set_modulus(data);
			System.out.println("¬ведите ваше сообщение:");
			message = keyboard.readLine();
			RSA_Server.encryptMsg(message);
			data = RSA_Server.get_message().toByteArray();
			out.writeInt(data.length);
			out.write(data);
			out.flush();
			
		}
		catch(Exception x) { x.printStackTrace(); }
	}
}
