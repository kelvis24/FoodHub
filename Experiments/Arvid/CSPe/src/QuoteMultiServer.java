
import java.io.*;
import java.net.*;
import java.util.*;

public class QuoteMultiServer extends Thread {

    protected DatagramSocket SOCK = null;
    public int QuoteCount = 0;
    public long Time_Interval = 6000;

    public String[] PirateQuotes = {
        "[Pirate Quote 1]",
        "[Pirate Quote 2]",
        "[Pirate Quote 3]",
        "[Pirate Quote 4]",
        "[Pirate Quote 5]",
        "[Pirate Quote 6]",
        "[Pirate Quote 7]",
        "[Pirate Quote 8]",
        "[Pirate Quote 9]",};

    public static void main(String[] args) throws IOException {
        QuoteMultiServer SERVER = new QuoteMultiServer();
        SERVER.start();
    }

    public QuoteMultiServer() throws IOException {
        SOCK = new DatagramSocket(444);
        System.out.println("Launched Multicast QOTD server Thread!");
        System.out.println("Waiting for client to connect...");
    }

    public void run() {
        while (true) {
            try {
                byte[] BUFFER = new byte[256];

                String MESSAGE = NextQuote();

                BUFFER = MESSAGE.getBytes();

                InetAddress IP_ADDRESS_GROUP = InetAddress.getByName("224.0.0.1");
                DatagramPacket PACKET = new DatagramPacket(BUFFER, BUFFER.length, IP_ADDRESS_GROUP, 444);

                SOCK.send(PACKET);

                try {
                    sleep(Time_Interval);
                } catch (InterruptedException X) { X.printStackTrace(); }
            } catch (IOException X) {
                X.printStackTrace();
                break;
            }
        }
        SOCK.close();
    }

    protected String NextQuote() {
        String MESSAGE = "";
        Date TheTime = new Date();

        if (QuoteCount >= PirateQuotes.length - 1) {
            MESSAGE = "\nFresh outta quotes mate! Have some TIME:\n" +
                TheTime.toString() + " .";
            System.out.print("\nAll quotes were SERVED! Dishing out sime TIME...");
        }
        else {
            MESSAGE = (QuoteCount+1) + ". " + PirateQuotes[QuoteCount];
            System.out.print("\nServing out QUOTE # " + (QuoteCount+1) + "!");
            QuoteCount++;
        }
        return MESSAGE;
    }

}
