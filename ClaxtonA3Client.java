
import java.util.*;
import java.io.*;
import java.net.*;

class ClaxtonA3Client {

  public static void main(String argv[]) throws Exception {
    ServerSocket serverSocket = new ServerSocket(33445);

    ArrayList DVR = readDVRFile();

    while (true) {
        Socket connectionSocket = serverSocket.accept();
        PrintStream out = new PrintStream(connectionSocket.getOutputStream());

        for(int i = 0; i<DVR.size(); i++) {
            out.println(DVR.get(i));
            System.out.println("Sent Data: " + DVR.get(i));
        }

       connectionSocket.close(); 
  }
}

    private static ArrayList readDVRFile() throws IOException {
        BufferedReader br = null;
        Scanner s = null;

        ArrayList DVR = new ArrayList();

        try {
            br = new BufferedReader(new FileReader("DVR.txt"));
            s = new Scanner(br);

            int numberOfNodes = s.nextInt();
            String dumbline = s.nextLine();

            for (int i=0; i<numberOfNodes; i++) {
                String newNode = s.nextLine();
                DVR.add(newNode);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return DVR;
    }
}