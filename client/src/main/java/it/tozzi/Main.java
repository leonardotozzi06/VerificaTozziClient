package it.tozzi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception  {
        System.out.println("Client attivo e pronto a collegarsi");

        Scanner s = new Scanner(System.in);
        
        Socket clientSocket = new Socket("localhost", 3000);

        String clientUsername = s.nextLine();

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        out.writeBytes(clientUsername + "\n");
        String intro_msg = in.readLine();
        System.out.println("\n" + intro_msg + "\n");
        if(intro_msg.equals("UTENTE GIA IN USO\n")) {
            System.exit(0);
        }
        
        String attempt;
        do {
            System.out.println("Inserisci un numero a tua scelta (compreso tra 0 e 100). Quando avrai indovinato, invia !");
            attempt = s.nextLine();

            out.writeBytes(attempt);

            s.nextLine();
        } while(attempt.equals("!"));


        s.close();
        clientSocket.close();
    }
}