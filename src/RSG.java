// Hannah Bish
// hjb5rd

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class RSG {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		while (true) {
			System.out.println("What do you want to do?");
			System.out.println("1: Generate a random episode " +
					"0: quit");
			int opt = stdin.nextInt();
			if (opt == 0) { //quitting
				System.out.println("The program is quitting");
				break;
			}
			if (opt == 1) {
				//System.out.println("Available grammars: " + findGrammars());
				//System.out.println("Which grammar would you like to use?");
				//String chosen = stdin.next();
				
				display(createStatement("xfiles.txt"));
				System.out.println("");
				/*
				String s1 = createStatement(chosen);
				display(s1);
				String s2 = createStatement(chosen);
				display(s2);
				String s3 = createStatement(chosen);
				display(s3);
				String s4 = createStatement(chosen);
				display(s4);
				String s5 = createStatement(chosen);
				display(s5);
				*/
			}
		}
	}

	public static String createStatement( String fileName ) {
		//System.out.println("loading grammar...");
		Grammar g = new Grammar();
		g.load(fileName);
		RandomSentence r = new RandomSentence();
		return r.generate(g, g.getStartSymbol());
	}

	// create a List that contains names of all g files in current directory
	private static ArrayList<String> findGrammars() {
		ArrayList<String> gfiles = new ArrayList<String>();
		File dir = new File(".");  // get File object for current directory
		String[] allFiles = dir.list(); // get names of files in directory
		for (String file : allFiles) {
			if (file.endsWith(".g")) {
				gfiles.add(file);
			}
		}
		return gfiles;
	}

	private static void display(String sentence) {
		String[] tokens = sentence.split("\\s+");
		int count = 0;
		int lineLen = 0;
		String outputString = "";
		while ( count < tokens.length ) {
			String term = tokens[count];
			lineLen += term.length();
			// some grammars allow -n to mean "new line"
			if ( term.equals("-n")) {
				term = "\n";
				lineLen = 0;
			}
			// break up long lines
			if ( lineLen > 60 ) {
				outputString += "\n" + term + " ";
				lineLen = 0;
			}
			else {
				outputString += term + " ";
			}
			++count;
		}
		// fix spacing before , and . 
		outputString = outputString.replaceAll(" ,", ",");
		outputString = outputString.replaceAll(" \\.", ".");
		// print final string
		System.out.println(outputString);
	}

}
