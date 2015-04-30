
import java.util.*;
import java.io.*;
import java.net.*;
class ClaxtonA3Client {

  public static void main(String argv[]) throws Exception {
    String clientSentence;
    ServerSocket serverSocket = new ServerSocket(12337);

    while (true) {
    Socket connectionSocket = serverSocket.accept();
    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    clientSentence = inFromClient.readLine();

    httpRequest(clientSentence, 80);
    }
  }

  public static void httpRequest(String w, int p) throws Exception {
    Socket socket = new Socket(w, p); //connects
    PrintStream out = new PrintStream(socket.getOutputStream());
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out.println( "GET " + p + " HTTP/1.0" );
    out.println();
    String line = in.readLine();

    while( line != null ) {
      System.out.println( line );
      line = in.readLine();
    }

    in.close();
    out.close();
    socket.close();
    }
}