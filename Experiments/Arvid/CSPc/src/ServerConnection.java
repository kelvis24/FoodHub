
import java.io.*;
import java.net.*;

public class ServerConnection implements Runnable {

    private Socket server;
    private BufferedReader in;

    public ServerConnection(Socket s) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = in.readLine();
                if (serverResponse == null) break;
                System.out.println("Server says: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
