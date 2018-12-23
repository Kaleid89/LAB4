import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	 public static void main(String[] arr) {
	        int serverPort = 4567; 
	        int input;
	        byte message[];
	        String address = "127.0.0.1", response;
	        RSA_Client.genPublicKey();
	        RSA_Client.genPrivateKey();
	        try {
	            InetAddress ipAddress = InetAddress.getByName(address); 
	            Socket socket = new Socket(ipAddress, serverPort); 
	            InputStream sin = socket.getInputStream();
	            OutputStream sout = socket.getOutputStream();
	            DataInputStream in = new DataInputStream(sin);
	            DataOutputStream out = new DataOutputStream(sout);
	            message = RSA_Client.get_exp().toByteArray();
	            out.writeInt(message.length);
	            out.write(message);
	            message = RSA_Client.get_modulus().toByteArray();
	            out.writeInt(message.length);
	            out.write(message);
	            message = new byte[in.readInt()];
	            in.read(message);
	            RSA_Client.set_message(message);
	            RSA_Client.decryptMsg(RSA_Client.get_message());
	            out.flush();
	                
	        } catch (Exception x) {
	            x.printStackTrace();
	        }
	    }
}
