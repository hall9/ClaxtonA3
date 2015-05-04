/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ClaxtonA3Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Option 1: ");
        System.out.println("Option 2: ");
        System.out.print("Option= ");
        int option = console.nextInt();

        //Read from WT.txt for starting node
        Node WT = readWTFile();

        if(option == 1) {
        //OPEN TCP and start keeping track of time (T1)
        //Request DVR Data from client (reading frist number of messages)

        ArrayList<Node> nodeList = WT.getNodes();

        Node DVR1 = nodeList.get(0);
        DVR1.addNode(new Node(3,3));
        DVR1.addNode(new Node(6,8));
        DVR1.addNode(new Node(5,6));

        Node DVR2 = nodeList.get(1);
        DVR2.addNode(new Node(3,11));
        DVR2.addNode(new Node(7,3));

        Node DVR3 = nodeList.get(2);
        DVR3.addNode(new Node(3,2));
        DVR3.addNode(new Node(2,8));
        DVR3.addNode(new Node(7,5));

        createTable(WT, nodeList.get(0));
        createTable(WT, nodeList.get(1));
        createTable(WT, nodeList.get(2));



        //Update Routing Table
        //Compute shortest node path.
        //Print Router Tables
        //Print Shortest Path


            //httpRequest("0.0.0.0", 33445);
        }
        else if(option == 2) {
        //Creat treads
            //Open TCP and start keeping track of time (T2)
                //Request DVR Data from client
            //Update Routing Table
                //Compute shortest node path.
                //Print Each table
        //Print Shortest Path
        }

        //Print total time taken (T1 or T2)
    }
    
    public static void httpRequest(String w, int p) {
            
    } 

    private static void createTable(Node base, Node root) {

        String table = "=====\n";
        table += "D N D\n";
        table += base.location + " 0 0";
        table += "\n" + root.location + " " + root.location + " " + root.weight;

        ArrayList<Node> nodeList = root.getNodes();
        for(int i = 0; i<nodeList.size(); i++) {
            table += "\n" + nodeList.get(i).location + " " + root.location + " " + nodeList.get(i).weight;
        }

       System.out.println(table); 
    }

    private static void shortestPath(Node base, Node root) {



    }

    private static Node readWTFile () throws IOException {
        BufferedReader br = null;
        Scanner s = null;

        Node WT = null;

        try {
            br = new BufferedReader(new FileReader("WT.txt"));
            s = new Scanner(br);

            WT = new Node(s.nextInt(),s.nextInt());

            while(s.hasNextInt()) {
                WT.addNode(new Node(s.nextInt(),s.nextInt()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return WT;
    }


static class Node{
    public int location;
    public int weight;
    private ArrayList<Node> nodeList;

    public Node(int location, int weight) {
        this.location = location;
        this.weight = weight;
        nodeList = new ArrayList<Node>();
    }

    public void addNode(Node node) {
        nodeList.add(node);
    }

    public ArrayList<Node> getNodes() {
        return nodeList;
    }
}
}
