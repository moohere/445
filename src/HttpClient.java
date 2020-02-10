import java.net.*;
import java.io.*;

public class HttpClient{
	static void getRequest(String host, int port, String path, boolean verbose) throws IOException {
		// Create a client socket to listen to the server
		Socket clientSocket = new Socket(host, port);
		
		// Initiate the writer/reader to write request and read response
		PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);		
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		// Write the request using the path and the host
		writer.print("GET " + path + " HTTP/1.0\r\n\r\n");
		writer.print("Host: " + host + "\r\n");
		writer.flush();
		
		//System.out.println("GET request sent.\r\n");

		// Read the response
		String line;
		if (verbose) {
			while ((line = reader.readLine()) != null)  {
				System.out.println(line);
			}
		} else {
			boolean doneHeader = false;
			while ((line = reader.readLine()) != null)  {
				if (line.equals("")) doneHeader = true;
				
				if (doneHeader) {
					System.out.println(line);
				} else {
					continue;
				}
			}
		}
				
		// Close the reader, writer, and the client socket
		writer.close();
		reader.close();
		clientSocket.close();
}
	
	static void postRequest(String host, int port, String path, String data, boolean verbose) throws IOException {
		// Create a client socket to listen to the server
		Socket clientSocket = new Socket(host, port);
		
		// Initiate the writer to write request
		PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
		
		// Write the request using the data
		writer.print("POST " + path + " HTTP/1.0\r\n");
		writer.print("Host: " + host + "\r\n");
		writer.print("Content-Length: " + data.length() + "\r\n\r\n");
		writer.print(data + "\r\n");
		writer.flush();
		
		// Used for debugging
//		System.out.println("POST " + path + " HTTP/1.0");
//		System.out.println("Host: " + host);
//		System.out.println("Content-Length: " + data.length() + "\r\n");
//		System.out.println(data);
//		
//		System.out.println("\nPOST request sent.\r\n");
		
		// Initiate the reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		// Read the response
		String line;
		if (verbose) {
			while ((line = reader.readLine()) != null)  {
				System.out.println(line);
			}
		} else {
			boolean doneHeader = false;
			while ((line = reader.readLine()) != null)  {
				if (line.equals("")) doneHeader = true;
				
				if (doneHeader) {
					System.out.println(line);
				} else {
					continue;
				}
			}
		}
		
		// Close the reader, writer, and the client socket
		writer.close();
		reader.close();
		clientSocket.close();
	}
	
	public static void main(String[] args) throws IOException {
		//getRequest("httpbin.org", 80, "http://httpbin.org/get?course=networking&assignent=1", true);
		
//		String data = "{\"Assignment\": 1}";
//		postRequest("httpbin.org", 80, "/post", data, true);
	}

}
