/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class ClaxtonA3Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Option 1: ");
        System.out.println("Option 2: ");
        int option = console.nextInt();

        //Create Table
        //Read from WT.txt for starting node
        //Populate tables

        if(option == 1) {
        //OPEN TCP
        //Request DVR Data from client (reading frist number of messages)
        //Update Routing Table
        //Compute shortest node path.
        //Print Router Tables
        //Print Shortest Path
        //this is printed as time T1
            httpRequest("0.0.0.0", 33445);
        }
        else if(option == 2) {
        //Creat treads
            //Open TCP
                //Request DVR Data from client
            //Update Routing Table
                //Compute shortest node path.
                //Print Each table
        //Print Shortest Path
        //this is printed as time T2
        }

        //Print total time taken, 
    }
    
    public static void httpRequest(String w, int p) throws Exception {
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
