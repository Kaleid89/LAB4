import java.math.BigInteger;

public class RSA_Server {
	private static BigInteger modulus;
	private static BigInteger exp;
	private static BigInteger message;
	
	public static void encryptMsg(String msg) {	
		BigInteger _msg = BigInteger.valueOf(Integer.parseInt(msg));
		_msg = _msg.modPow(exp, modulus);
		message = _msg;
	}
	
	public static void set_modulus(byte[] mod) {
		modulus = new BigInteger(mod);
	}
	
	public static void set_exp(byte[] _exp) {
		exp = new BigInteger(_exp);
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
	
}
