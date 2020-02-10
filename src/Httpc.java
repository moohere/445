import java.io.*;
import java.net.*;

public class Httpc {
	public static void main(String[] args) throws IOException {
		// Takes the command line args
		String[] commands = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			commands[i] = args[i];
		}
		// general
		if (commands[0].equalsIgnoreCase("help") && commands.length == 1) {
			System.out.println("httpc is a curl-like application but supports HTTP protocol only.");
			System.out.println("Usage:");
			System.out.println("\thttpc command [arguments]");
			System.out.println("The commands are:");
			System.out.println("\tget\t executes a HTTP GET request and prints the response.");
			System.out.println("\tpost\t executes a HTTP POST request and prints the response.");
			System.out.println("\thelp\t print this screen.\n");
			System.out.println("Use \"httpc help [command]\" for more information about a command.");
		} 
		// get usage
		else if (commands[0].equalsIgnoreCase("help") && commands[1].equalsIgnoreCase("get") && commands.length == 2) {
			System.out.println("httpc help get");
			System.out.println("\nusage: httpc get [-v] [-h key:value] URL");
			System.out.println("\nGet executes a HTTP GET request for a given URL.");
			System.out.println("\t-v\t\tPrints the detail of the response such as protocol, status, and headers.");
			System.out.println(("\t-h key:value\tAssociates headers to HTTP Request with the format 'key:value'."));
		} 
		// post usage
		else if (commands[0].equalsIgnoreCase("help") && commands[1].equalsIgnoreCase("post") && commands.length == 2) {
			System.out.println("httpc help post");
			System.out.println("\nusage: httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL");
			System.out.println("\nPost executes a HTTP POST request for a given URL with inline data or form file.");
			System.out.println("\t-v\t\tPrints the detail of the response such as protocol, status, and headers.");
			System.out.println("\t-h key:value\tAssociates headers to HTTP Request with the format 'key:value'.");
			System.out.println("\t-d string\tAssociates an inline data to the body HTTP POST request.");
			System.out.println("\t-f file\tAssociates the content of a file to the body HTTP POST request.");
			System.out.println("\nEither [-d] or [-f] can be used but not both.");
		} 
		// get request
		else if (commands[0].equalsIgnoreCase("get") && commands.length <= 3) {
			// with query parameters
			if ( commands.length == 2 ) {
				HttpClient.getRequest("httpbin.org", 80, commands[1], false);
			} 
			// with verbose option
			else if (commands.length == 3 && commands[1].equals("-v")) {
				HttpClient.getRequest("httpbin.org", 80, commands[2], true);
			}
		} 
		// post request
		else if (commands[0].equalsIgnoreCase("post")) {
			// with query parameters
			if (commands.length == 6 && commands[1].equalsIgnoreCase("-h") && commands[3].equalsIgnoreCase("-d")) {
				HttpClient.postRequest("httpbin.org", 80, commands[5], commands[4], false);
			} 
			// with verbose option
			else if (commands.length == 7 && commands[1].equalsIgnoreCase("-v") && commands[2].equalsIgnoreCase("-h") && commands[4].equalsIgnoreCase("-d")) {
				HttpClient.postRequest("httpbin.org", 80, commands[6], commands[5], true);
			}
		} 
		// all other commands
		else {
			System.out.println("\nPlease enter a valid command. For help, type 'java Httpc help'.");
		}
	}
}
