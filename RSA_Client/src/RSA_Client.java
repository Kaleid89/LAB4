import java.math.BigInteger;
import java.util.Random;;

public class RSA_Client {
	private static BigInteger modulus;
	private static BigInteger phi;
	private static BigInteger exp;
	private static BigInteger d;
	private static BigInteger message;
	
	public static void genPublicKey () {
		int p = 0,q = 0;
		Random rand = new Random();
		boolean isChosen = false;
		while(!isChosen) {
			p = rand.nextInt(50)+3;
			for(int i = 2;i<p;i++) {
				if(p%i==0 && p!=i) {
					isChosen = false;
					break;
				}
				isChosen = true;
			}
		}
		isChosen = false;
		while(!isChosen) {
			q = rand.nextInt(50)+3;
			for(int i = 2;i<q;i++) {
				if(q%i==0 && q!=i) {
					isChosen = false;
					break;
				}
				isChosen = true;
			}
		}
		
		System.out.println("p = "+p+" q = "+q);
		modulus = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
		System.out.println("modulus = "+modulus);
		phi = BigInteger.valueOf(p-1).multiply(BigInteger.valueOf(q-1));
		System.out.println("phi = "+phi);
		isChosen = false;
		while(!isChosen) {
			exp = BigInteger.valueOf(rand.nextInt(Integer.parseInt(phi.toString())-3)).add(BigInteger.valueOf(3));
			if(phi.mod(exp).abs() == BigInteger.ZERO)continue;
			int _exp = Integer.parseInt(exp.toString());
			for(int i = 2;i<_exp;i++) {
				if(exp.mod(BigInteger.valueOf(i))==BigInteger.ZERO && exp!=BigInteger.valueOf(i)) {
					isChosen = false;
					break;
				}
				isChosen = true;
			}
		}
		System.out.println("exp = "+exp);
	}
	
	public static void genPrivateKey() {
		Random rand = new Random();
		boolean isChosen = false;
		while(!isChosen) {
				d = BigInteger.valueOf(rand.nextInt(Integer.parseInt(phi.pow(1).toString())));
				BigInteger temp = d.multiply(exp).mod(phi);
				if(temp.equals(BigInteger.ONE)) {
					isChosen = true;
					break;
				}
		}
	} 
	public static void decryptMsg(BigInteger msg) {	
		System.out.println("message = "+message);
		System.out.println("d= "+d);
		message = msg.modPow(d, modulus);
		System.out.println(message);
	}
	
	public static BigInteger get_exp() {
		return exp;
	}
	
	public static BigInteger get_modulus() {
		return modulus;
	}
	
	public static BigInteger get_message() {
		return message;
	}
	
	public static void set_message(byte[] _message) {
		message = new BigInteger(_message);
	}
}

