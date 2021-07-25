import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class oopClient {

    public static void main(String[] args) throws Exception {

        Connection C1 = new Connection("127.0.0.1", 9081);
        ChatWindow W1 = new ChatWindow(C1);

        String str = C1.nis.readLine();
        while (!str.equals("End")) {
            W1.ta.append(str + "\n");
            str = C1.nis.readLine();
        }
        W1.ta.append("\n Client Signing Off");
        C1.getSocket().close();
    }

}



class Connection {

    private String address;
    private int port;
    private Socket soc;
    public PrintWriter nos;
    public BufferedReader nis;

    Connection(String address, int port) throws Exception {
        this.address = address;
        this.port = port;
        soc = new Socket("127.0.0.1", 9081);
        nos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);
        nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
    }

    public String getAddress(){
        return address;
    }

    public int getPort(){
        return port;
    }

    public Socket getSocket(){
        return soc;
    }
}

class ChatWindow extends JFrame implements ActionListener{

    JButton b1;
    JTextArea ta;
    JTextField tf;
    JPanel p1;
    Connection c;

    ChatWindow(Connection c) {
        this.c = c;
        String ip = c.getAddress();
        int port = c.getPort();
        setTitle("Connected to "+ip+" at port "+port);
        b1 = new JButton("Ok");
        ta = new JTextArea(10, 10);
        ta.setEditable(false);
        tf = new JTextField(20);
        p1 = new JPanel();
        p1.add(tf);
        p1.add(b1);
        add(p1, BorderLayout.SOUTH);
        add(ta);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener(this);
        tf.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = tf.getText();
        ta.append(str + "\n");
        c.nos.println(str);
        tf.setText("");
        if (str.equals("End")) {
            c.nos.close();
            System.exit(0);
        }
    }
}