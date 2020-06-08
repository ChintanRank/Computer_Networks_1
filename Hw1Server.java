/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Hw1Server {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        try{
        	ServerSocket ss=new ServerSocket(portNumber);

	    while(true){
	    	Socket cs=ss.accept();   
              ClientWorker w=new ClientWorker(cs);
	      Thread t=new Thread(w);
	      t.start();
	    }
        
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

class ClientWorker implements Runnable {
  private Socket cs;

//Constructor
  ClientWorker(Socket cs) {
    this.cs = cs;
  }

  public void run(){
    String line;
    BufferedReader in = null;
    PrintWriter out = null;
    ArrayList<String> list=null;
    try{
      in = new BufferedReader(new 
        InputStreamReader(cs.getInputStream()));
      out = new PrintWriter(cs.getOutputStream(),true);
      String userInput;
		String remoteHost = null;
		String filePath=null;
		int count1,count2,i,j;
		if((userInput=in.readLine())!=null){
			StringTokenizer st = new StringTokenizer(userInput);		
			count1=st.countTokens();
			String a[]=new String[count1];
			i=0;
			while(i<count1) {
				a[i]=st.nextToken();
				i++;
				}
			
			StringTokenizer s2=new StringTokenizer(a[1],"/") ;
			remoteHost=s2.nextToken();
			filePath=a[1].replace(remoteHost, "");

		}
		
		Socket remoteSocket=new Socket(remoteHost,80);
		 //(remoteSocket.getRemoteSocketAddress(), 25);
		PrintWriter remoteOut = new PrintWriter(remoteSocket.getOutputStream(),true);
		BufferedReader remoteIn = new BufferedReader(new InputStreamReader(remoteSocket.getInputStream()));
		
	    System.out.println("Connecting to : "+ remoteHost + "\n" + "Please wait for 3 to 4 minutes as the average runtime for server like google to run was 4 minute");
	    
	    remoteOut.println("GET "+ filePath +" HTTP/1.1"  );
		remoteOut.println("Host: " + remoteHost );
		remoteOut.print("\r\n");
		
		
		//InputStream iis =new DataInputStream(remoteSocket.getInputStream());
		//DataInputStream dis=new DataInputStream(remoteSocket.getInputStream());
		
         String remoteInString;
		
		if((remoteInString=remoteIn.readLine())!=null) {
		Stream<String> hello=remoteIn.lines();
	    Iterator it=hello.iterator();
	   list = new ArrayList<String>(); 
	    while(it.hasNext()) {
	    	Object element=it.next();
			list.add((String)element);
		}
		
	 
	    
		}
		
    } catch (IOException e) {
      System.out.println("in or out failed");
      System.exit(-1);
    }

    while(true){
      try{
         Iterator its=list.iterator();
	    
	    while(its.hasNext()) {
	    	out.println(its.next());
	    	
	    }
	    	
	    System.out.println("successful");
	    
	    break;
       }catch (Exception e) {
        System.out.println("Read failed");
        System.exit(-1);
       }
    }
  }
}