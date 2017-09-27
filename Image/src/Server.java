import java.rmi.Naming;

public class Server {
	public static void main(String [] arguments) {
		try {
			Picture1 call1 = new PictureRemote1();
			Picture2 call2 = new PictureRemote2();
			Picture3 call3 = new PictureRemote3();
			Picture4 call4 = new PictureRemote4();
			
			Naming.rebind("rmi://localhost:5000/shovon", call1);
			Naming.rebind("rmi://localhost:5001/shovon", call2);
			Naming.rebind("rmi://localhost:5002/shovon", call3);
			Naming.rebind("rmi://localhost:5003/shovon", call4);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}