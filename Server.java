import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Server Signing ON");
        ServerSocket ss = new ServerSocket(9081);
        Socket soc = ss.accept();
        System.out.println("Server Says Connection Established");
        BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter nos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
        

        String str = nis.readLine();
        while (!str.equals("End")) {
            System.out.println("Server Recieved " + str);
            nos.println(str);
            str = nis.readLine();
        }
        nos.println(str);
        System.out.println("Server Singing OFF");
        ss.close();
    }
}