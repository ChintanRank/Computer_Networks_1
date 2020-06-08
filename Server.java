import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Server {

	public static void main(String args[]) {
	int portnumber=Integer.parseInt(args[0]);
	
	try {
		ServerSocket ss=new ServerSocket(portnumber);
		Socket cs=ss.accept();
		PrintWriter out = new PrintWriter(cs.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
	    
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
		
		
		File file=new File("/Users/Chintan/Desktop/cs682/aaa.html");
        BufferedWriter writer =new BufferedWriter(new FileWriter(file));
        BufferedReader remoteInn = new BufferedReader(new FileReader("/Users/Chintan/Desktop/cs682/aaa.html"));
		
		
		
		
	    System.out.println("Connecting to : "+ remoteHost + "\n" + filePath);
	    
	    remoteOut.println("GET "+ filePath +" HTTP/1.1"  );
		remoteOut.println("Host: " + remoteHost );
		remoteOut.println("\r\n");
		
		//ObjectOutputStream obs = new ObjectOutputStream(cs.getOutputStream());
		//InputStream iis =new DataInputStream(remoteSocket.getInputStream());
		
		DataInputStream dis=new DataInputStream(remoteSocket.getInputStream());
		DataOutputStream dout=new DataOutputStream(cs.getOutputStream());
		
	/*	System.out.println(dis.read()+" "+dis.available());
		
		 while(true) {
			 int a=dis.read();
			 System.out.println(dis.available());
			 dout.write(a);
			 dout.flush();
			 if(a==-1) {
				 break;
			 }
		 }*/
		
		int waste=dis.read();
		int size=dis.available();
		
		System.out.println("size :"+size);
		//List<Byte> list=new ArrayList<Byte>();
		   
		byte[] b=new byte[size];
		dis.readFully(b,0,size);
		
		 while(true) { 
			 System.out.println(size + "  "+ b.length );
			 if(dis.read()==-1) {
				 break;
			 }
		 }
		 // String s=new String(b);
	     //System.out.println(s);
		 int a=0;
		 int c=1;
		 while(b.length!=a) {
			 dout.write(b[a]);
			//dout.writeByte(b[a]);
			dout.flush();
		     a++;
			System.out.println(a);
			c++;
			System.out.println(c);
		 }
		
		 
		
		
		 /* List<Byte> list=new ArrayList<Byte>();
		   ByteArrayOutputStream bais=new ByteArrayOutputStream(); 
		 while(true) {
			 int a=dis.read();
			 System.out.println(dis.available());
			 list.add((byte)a);
			 if(a==-1) {
				 break;
			 }
		 }
		 byte b[]=new byte[list.size()];
		
		 int q;*/
		
		
	
		
		
		//System.out.println(bais.size() + "\r this is available : " + dis.available() + "\n" + );
	//	System.out.println(b.length + "hello\br" + " "+ "hello\rf");
		System.out.print("\r\b\b\b\by");
		
		
		
		
		
	
		/*int size=0;
		while(remoteIn.read()!=-1) {
			size=size+remoteIn.read();
			
		}*/
		
		
		
		String remoteInString;
	
		
		
		
		/*int size=0;
		
		while((remoteInString=remoteIn.readLine())!=null) {
			size=size+1;
			writer.write(remoteInString);
			writer.newLine();
			writer.flush();
		}
			
		
		System.out.println(size);
		String remoteInnString;
		while((remoteInnString=remoteInn.readLine())!=null) {
			System.out.println(remoteInnString);
		}
		*/
		
		
		
		
		
		
		
	/*	if((remoteInString=remoteIn.readLine())!=null) {
			
		Stream<String> hello=remoteIn.lines();
	    Iterator it=hello.iterator();
	    ArrayList<String> list = new ArrayList<String>(); 
	    while(it.hasNext()) {
	    	Object element=it.next();
			list.add((String)element);
		}
		
	    
	    int size=list.size();
	    System.out.println( size + "\n" );
	    Iterator its=list.iterator();
	    
	    
	    while(its.hasNext()) {
	    	obs.writeObject(size);
	    	obs.flush();
	    	out.println(its.next());
	    	
	    }
	    	
		}*/
		
		
	}
	catch(IOException e){
      System.out.println(e.getMessage());
	}
	catch(Exception e) {System.out.println("error :" + e);}
}
}
