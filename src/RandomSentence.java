public class RandomSentence {

	private String text;

	// create a random sentence from a grammar
	public String generate(Grammar g, String symbol) {
		String prod = g.getRandProduction(symbol);
		
		// if there is a nonterminal value, call again
		if (prod.contains("<")) {
			String[] words = prod.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				if (words[i].contains("<")) {
					words[i] = generate(g, words[i]);
				}
			}
			prod = "";
			for (int i = 0; i < words.length; i++) {
				prod = prod + words[i] + " ";
			}
		}
		this.text = prod;
		return prod;
	}

	public String toString() {
		System.out.println(text);
		return text;
	}

}
