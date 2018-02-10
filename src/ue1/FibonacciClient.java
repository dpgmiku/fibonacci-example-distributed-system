/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue1;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author miku
 */
public class FibonacciClient {
    
    private static Integer port = 5678;
    private static String ip = "";
    private static Integer fibZahl;
    
    


    
 public static void main( String[] args ) {
     System.out.println("Fibonaccirechner");
  Scanner eingabe = new Scanner(System.in);
  ip = args[0];  
 if (args.length>1){
     port = Integer.parseInt(args[1]);    
  }
 
 
 //Streams

Boolean var= true;
while(var){
System.out.print("$>");
String a = eingabe.nextLine().toLowerCase().trim();

if (a.contains("berechne")) {
 String[] b = a.split(" ");

 if (b.length == 2 && b[0].equals("berechne")){
     try {
     fibZahl = Integer.parseInt(b[1]);
     if (fibZahl < 0 || fibZahl > 40 ) {
         System.out.println("ungültiger Zahlenbereich");
     }
     else {
         try{
     Socket client = new Socket(ip,port);
DataOutputStream out = new DataOutputStream(client.getOutputStream());
DataInputStream  in = new DataInputStream(client.getInputStream());
     out.writeInt(fibZahl);
     
     Integer zahl = in.readInt();
         
 
     switch (zahl){
         case -1: System.out.println("Fehlerhafte Eingabe"); break;
         case -2: System.out.println("ungültiger Zahlenbereich"); break;
         default: System.out.println(zahl);
         
         
     }
     in.close();
     out.close();
     }
     catch (IOException e){
     System.out.println(e);
     System.exit(-2);
         
     }
     }
     }
     catch (NumberFormatException e) {
         
     System.out.println("Fehlerhafte Eingabe");
     
}
 }
 else {
 System.out.println("Bitte geben Sie nur einen Parameter bei berechne");    
     
 }
}
else {
  switch(a){
 case "ende": 
      
	var=false;
	break;
 case "hilfe": System.out.println("Geben sie 'hilfe', berechne <zahl>' oder 'ende' ein!");
	break;
 default: System.out.println("Falsche Eingabe! Geben sie 'hilfe' fuer Hilfestellung ein!");
					}  
    
    
    
    
}


    
}
 
 
       
                 
              

     
 }
 
}

    
    

