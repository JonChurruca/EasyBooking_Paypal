package PaypalServices;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPaymentServer extends Remote{

	Integer proceedPayment(String userEmail) throws RemoteException;
		
}
