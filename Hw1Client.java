import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class Hw1Client {

public static void main(String args[]){	
	String hostname=args[0];
	int portnumber=Integer.parseInt(args[1]);
	
	try {
	Socket socket= new Socket(hostname,portnumber);
         
	 PrintWriter out =
             new PrintWriter(socket.getOutputStream(), true);
         BufferedReader in =
             new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
         BufferedReader stdIn =   new BufferedReader( new InputStreamReader(System.in));
         
         File file=new File("/Users/Chintan/Desktop/cs682/hello.html");
         BufferedWriter writer =new BufferedWriter(new FileWriter(file));
         
     
         DataInputStream dis=new DataInputStream(socket.getInputStream());
         
         
         BufferedReader inp =
                 new BufferedReader(
                     new InputStreamReader( new DataInputStream(socket.getInputStream())));
         
         String userInput;
         if((userInput=stdIn.readLine())!=null){
        	 out.println(userInput); 	 
         }
         
         String serverOutput;
         //ObjectInputStream obs = new ObjectInputStream(socket.getInputStream());
         
        /*while((serverOutput=in.readLine())!=null) {
        	 
        	 System.out.println(serverOutput);
        	 //int size = (int) obs.readObject();
        	 //System.out.println(size);
        	 writer.write(serverOutput);
        	 writer.newLine();
        	
        	
         }
	
         writer.close();*/
        
         
         while(inp.readLine()!=null) {
        	 System.out.println(inp.readLine());
        	
         }
         
         
       /*  while(true) {
			 String a=new String(dis.read);
			 System.out.println(dis.available() );
			//System.out.println(a);
			 if(a==-1) {
				 break;
			 }
		 }*/
         
         
}
	
	catch (UnknownHostException e) {
        System.err.println("Don't know about host " + hostname);
        System.exit(1);
    } catch ( Exception e) {
        System.err.println("Couldn't get I/O for the connection to " +
            hostname);
        System.exit(1);
    } 
}
}
