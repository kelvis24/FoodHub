
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/*
   A client connects, and this responds by sending out the current date.
*/
public class Server {

    private static String[] names = {"Wily", "Felix", "Carlsbad", "Hobob"};
    private static String[] adjs = {"the gentle", "the un-gentle", "the overwrought", "the urbane"};
    private static final int PORT = 9090;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            System.out.println("Waiting for Client Connection.");
            Socket client = listener.accept();
            System.out.println("Connected to Client.");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }

    public static String getRandomName() {
        String name = names[(int)(Math.random()*names.length)];
        String adj = adjs[(int)(Math.random()*adjs.length)];
        return name + " " + adj;
    }

}
