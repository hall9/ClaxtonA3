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

    public static int alive = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Option 1: Read all from Client, then print tables.");
        System.out.println("Option 2: Read each, printing each at a time via threads.");
        System.out.print("Option = ");
        int option = console.nextInt();

        long startTime = System.currentTimeMillis();
        //Read from WT.txt for starting node
        Node WT = readWTFile();

        if(option == 1) {
        //OPEN TCP and start keeping track of time (T1)
        //Request DVR Data from client (reading frist number of messages)
        httpRequest("0.0.0.0", 33445, WT, 1);

        //Update Routing Table
        //Compute shortest node path.
        //Print Router Tables
        //Print Shortest Path

        //Print total time taken (T1)
        long endTime = System.currentTimeMillis();
        System.out.println("T1: " + (endTime - startTime) + " milliseconds");
        }
        else if(option == 2) {
        //Creat treads
            //Open TCP and start keeping track of time (T2)
                //Request DVR Data from client
            //Update Routing Table
                //Compute shortest node path.
                //Print Each table
        //Print Shortest Path
        httpRequest("0.0.0.0", 33445, WT, 2);

        Boolean running = true;
        while (running)
        //Print total time taken (T2)
            if (alive == 0) {
                long endTime = System.currentTimeMillis();
                System.out.println("T2: " + (endTime - startTime) + " milliseconds");
                running = false;
            }
        }
    }
    
    public static void httpRequest(String w, int p, Node base, int option) throws Exception {
        Socket socket = new Socket(w,p);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner s = new Scanner (in);
        //System.out.println(in.readLine());

        ArrayList<Node> newNodeList = new ArrayList<Node>();

        while (s.hasNextLine()) {
            Scanner line = new Scanner(s.nextLine());
            Node newNode = new Node(line.nextInt(), line.nextInt());
            newNodeList.add(newNode);

            while(line.hasNextInt()) {
                newNode.addNode(new Node(line.nextInt(), line.nextInt()));
            }

            if (option == 2) {
                Runnable r = new printTableThread(base, newNode);
                new Thread(r).start();
            }
        }

        if (option == 1) {
            for (int i=0; i<newNodeList.size(); i++) {
                printTable(base, newNodeList.get(i));
            }
            for (int i=0; i<newNodeList.size(); i++) {
                shortestPath(base, newNodeList.get(i));
            }
        }
    } 

    protected static void shortestPath(Node base, Node root) {
        for (int i=0; i<base.getNodes().size(); i++) {
            if (base.getNodes().get(i).location == root.location && base.location != base.getNodes().get(i).location) {
                String table = "===== Updated Table\n";
                table += "D N D\n";
                table += base.location + " 0 0";
                table += "\n" + root.location + " " + root.location + " " + base.getNodes().get(i).weight;

                int baseToRootWeight = base.getNodes().get(i).weight;

                ArrayList<Node> nodeList = root.getNodes();
                for(int z = 0; z<nodeList.size(); z++) {
                    table += "\n" + nodeList.get(z).location + " " + root.location + " " + (nodeList.get(z).weight + baseToRootWeight);
                }
                    System.out.println(table);
            }
        }
    }

    private static void shortestPathToNode () {

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

    protected static void printTable(Node base, Node root) {
       for (int i=0; i<base.getNodes().size(); i++) {
            if (base.getNodes().get(i).location == root.location) {
                String table = "=====\n";
                table += "D N D\n";
                table += base.location + " 0 0";
                table += "\n" + root.location + " " + root.location + " " + base.getNodes().get(i).weight;

                ArrayList<Node> nodeList = root.getNodes();
                for(int z = 0; z<nodeList.size(); z++) {
                    table += "\n" + nodeList.get(z).location + " " + root.location + " " + nodeList.get(z).weight;
                }
                    System.out.println(table);
            }
        }
    }

     public static class printTableThread implements Runnable {
        private Node base;
        private Node root;

        public printTableThread (Node base, Node root) {
            this.base = base;
            this.root = root;
        }

        public void run() {
            alive++;
            printTable(base, root);
            shortestPath(base, root);
            alive--;
        } 
    }

    public static class Node{
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
