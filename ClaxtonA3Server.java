/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Tommy
 */
import java.util.*;
import java.io.*;
import java.net.*;


/**
 *
 * @author Tommy
 */
public class ClaxtonA3Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the name of a web server: ");
        String w = console.nextLine();
        System.out.println("Enter 1: HTTP Get Request to Web via as client");
        System.out.println("Enter 2: Connect to Localhost to send HTTP get Request");
        int selection = console.nextInt();
        if(selection == 1) {
            System.out.println("Sending HTTP as client now...");
            httpRequest(w, 80, "GET " + 80 + " HTTP/1.0");
            //from http://www.javaworld.com/article/2853780/java-app-dev/socket-programming-for-scalable-systems.html
        }else if(selection == 2) {
            System.out.println("Connecting Localhost Server now...");
            System.out.println("Request viewable in server output Stream! Not here!");
            httpRequest("0.0.0.0", 12337, w);
        }
    }
    
    public static void httpRequest(String w, int p, String message) throws Exception {
            Socket socket = new Socket(w, p); //connects
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(message);
            out.println();
            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            in.close();
            out.close();
            socket.close();
    }  
}
