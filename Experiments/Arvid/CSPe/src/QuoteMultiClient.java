
import java.io.*;
import java.net.*;
import javax.swing.*;

public class QuoteMultiClient {

    public static JButton B_CONNECT = new JButton("CONNECT");
    public static JFrame MainWindow = new JFrame("DataGram Quote Server");
    public static JScrollPane SP_OUTPUT = new JScrollPane();
    public static JTextArea TA_OUTPUT = new JTextArea();

    public static void main(String[] args) {
        BuildGui();
    }

    public static void CONNECT() {
        try {
            MulticastSocket SOCK = new MulticastSocket(444);

            InetAddress IP_ADDRESS = InetAddress.getByName("224.0.0.1");
            SOCK.joinGroup(IP_ADDRESS);

            DatagramPacket PACKET;

            for (int i = 0; i < 3; i++) {
                byte[] buf = new byte[256];
                PACKET = new DatagramPacket(buf, buf.length);
                SOCK.receive(PACKET);

                String MESSAGE = new String(PACKET.getData(), 0, PACKET.getLength());
                TA_OUTPUT.append("\n\nToday's QUOTE: " + MESSAGE);
            }
            SOCK.leaveGroup(IP_ADDRESS);
            SOCK.close();
        } catch (IOException X) {
            System.out.println(X);
        }
    }

    public static void BuildGui() {
        MainWindow.setSize(410,280);
        MainWindow.setLocation(200,200);
        MainWindow.setResizable(false);
        MainWindow.setBackground(new java.awt.Color(255,255,255));
        MainWindow.getContentPane().setLayout(null);

        B_CONNECT.setBackground(new java.awt.Color(0,0,255));
        B_CONNECT.setForeground(new java.awt.Color(255,255,255));
        MainWindow.getContentPane().add(B_CONNECT);
        B_CONNECT.setBounds(150,15,110,25);

        TA_OUTPUT.setLineWrap(true);
        SP_OUTPUT.setHorizontalScrollBarPolicy(
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_OUTPUT.setVerticalScrollBarPolicy(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_OUTPUT.setViewportView(TA_OUTPUT);
        MainWindow.getContentPane().add(SP_OUTPUT);
        SP_OUTPUT.setBounds(10,45,380,180);

        MainWindow_Action();

        MainWindow.setVisible(true);
    }

    public static void MainWindow_Action() {
    B_CONNECT.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    CONNECT();
                }
            }
        );
    }

}
