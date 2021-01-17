package PaypalServices;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PaymentServer extends UnicastRemoteObject implements IPaymentServer {
	

	private static final long serialVersionUID = 1L;
	//private IAuthorizationService; 
	protected List<String> paypalUsers = new ArrayList<>(); 
	Integer paymentID = 0; 
	
	public PaymentServer() throws RemoteException{
		// we create some default values 
		paypalUsers.add("haizearodriguez@opendeusto.es"); 
		paypalUsers.add("unai.mendiondo@opendeusto.es"); 
		paymentID += 1; 
		
	}

	
	@Override
	public Integer proceedPayment(String userEmail) {
		
		
		if (paypalUsers.equals(userEmail)) {
			return paymentID;
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		
		//Create the name of the server 
		// EX: name = "//120.0.1:1099/Hello"
		
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {	
			IPaymentServer pServer = new PaymentServer();
			Naming.rebind(name, (Remote) pServer);
			System.out.println("* Server '" + name + "' active and waiting...");
			
			
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}



}
