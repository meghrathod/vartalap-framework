import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        
        serverStarter first = new serverStarter(9081);
        serverStarter second = new serverStarter(9082);
        first.start();
        second.start();

        
    }
}

class serverStarter extends Thread{
    int port;
    serverStarter(int port){
        this.port = port;
    }

    public void run(){
        System.out.println("Server Signing ON");
        ServerSocket ss;
        try {
            ss = new ServerSocket(port);
            Socket soc = ss.accept();
            System.out.println("Server at "+ port + " Says Connection Established");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}