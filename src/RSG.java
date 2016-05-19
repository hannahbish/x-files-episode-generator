import java.util.Scanner;

public class RSG {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		// get user input 
		while (true) {
			System.out.println("What do you want to do?");
			System.out.println("1: Generate a random episode " +
					"0: quit");
			int opt = stdin.nextInt();
			if (opt == 0) { // quitting
				System.out.println("The program is quitting");
				break;
			}
			if (opt == 1) { // generate sentence
				display(createStatement("xfiles.txt"));
				System.out.println("");
			}
		}
		
		stdin.close();
	}

	// generate a random sentence from a grammar file
	public static String createStatement( String fileName ) {
		Grammar g = new Grammar();
		g.load(fileName);
		RandomSentence r = new RandomSentence();
		return r.generate(g, g.getStartSymbol());
	}
	
	// clean up the sentence for display
	private static void display(String sentence) {
		String[] tokens = sentence.split("\\s+");
		
		int count = 0;
		int lineLen = 0;
		String outputString = "";
		
		while ( count < tokens.length ) {
			String term = tokens[count];
			lineLen += term.length();
			
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
