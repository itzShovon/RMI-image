import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Picture2 extends Remote{
//	public void show() throws RemoteException;
	public byte[] process(byte[] image) throws RemoteException;
	public int isFree() throws RemoteException;
}