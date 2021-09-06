
import java.io.*;
import java.net.*;
import java.util.*;

public class QuoteSingleServer extends Thread {

    protected DatagramSocket SOCK = null;
    public int QuoteCount = 0;

    public String[] PirateQuotes = {
        "A dangerous song to be singing, for one ignorant of its meaning. - Pirates of the Carribean",
        "Now and then we had a hope that if we lived and were good, God would permit us to be pirates. -Life on the ...",
        "The average man will bristle if you say his father was dishonest, but he will brag a little if he discovers...",
        "You will always remember this as the day you almost caught Captain Jack Sparrow - Pirates of the Carribean"
    };

    public static void main(String[] args) throws IOException {
        QuoteSingleServer SERVER = new QuoteSingleServer();
        SERVER.start();
    }

    public QuoteSingleServer() throws IOException {
        SOCK = new DatagramSocket(444);
        System.out.println("Launched QOTD server Thread!");
        System.out.println("Waiting for client to connect...");
    }

    public void run() {
        while (true) {
            try {
                byte[] BUFFER = new byte[256];

                DatagramPacket PACKET = new DatagramPacket(BUFFER, BUFFER.length);
                SOCK.receive(PACKET);

                String MESSAGE = NextQuote();

                BUFFER = MESSAGE.getBytes();

                InetAddress IP_ADDRESS = PACKET.getAddress();
                int PORT = PACKET.getPort();
                PACKET = new DatagramPacket(BUFFER, BUFFER.length, IP_ADDRESS, PORT);

                SOCK.send(PACKET);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        SOCK.close();
    }

    protected String NextQuote() {
        String MESSAGE = "";
        Date TheTime = new Date();

        if (QuoteCount >= PirateQuotes.length) {
            MESSAGE = "\nFresh outta quotes mate! Have some TIME:\n" +
                TheTime.toString() + " .";
            System.out.println("\nAll quotes were SERVED! Dishing out some TIME...");
        }
        else {
            MESSAGE = (QuoteCount+1) + ". " + PirateQuotes[QuoteCount];
            System.out.print("\nServing out QUOTE # " + (QuoteCount+1) + "!");
            QuoteCount++;
        }
        return MESSAGE;
    }

}
