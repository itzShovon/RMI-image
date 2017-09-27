import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PictureRemote2 extends UnicastRemoteObject implements Picture2 {
	public PictureRemote2() throws RemoteException{
		super();
	}
	
//	public void show() {
//		try {
//			File sourceimage = new File("F:\\Users\\cse\\eclipse-workspace\\RMI\\src\\Demo.jpg");
//			Image image = ImageIO.read(sourceimage);
//		    System.out.println("Done Image");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//	}
	
	public byte[] process(byte[] image) {
		try {
			System.out.println("Byte Image Just Received");
//			return image;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return image;
	}
	
	public int isFree() {
		return 0;
	}
}