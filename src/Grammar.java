// Hannah Bish
// hjb5rd

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grammar {

	private String start;
	private Map<String, List<String>> productions;

	public void load(String fileName) {
		int count = 0;
		try {			
			//String fileName1 = System.getProperty("user.dir") + "/" + fileName;
			File file = new File(System.getProperty("user.dir") + "/" + fileName);  
			Scanner sc = new Scanner(file);  
			while (sc.hasNext()) {
				String line = sc.nextLine();
				//System.out.println(line);
				if (line.contains("{")) {
					// if it's the start
					String line2 = sc.nextLine();
					//System.out.println("line2: " + line2);
					if (line2.contains("<") && count == 0) {
						String sym = line2.substring(line2.indexOf("<"), 
								line2.indexOf(">") + 1);
						this.setStartSymbol(sym);
						count++;
						while (!sc.hasNext("}")) {
							String line3 = sc.nextLine();
							//System.out.println("line3 " + line3);
							this.addProduction(sym, line3);
						}
					}
					//not the start
					if (line2.contains("<") && count != 0) {
						String sym = line2.substring(line2.indexOf("<"), 
								line2.indexOf(">") + 1);
						count++;
						while (!sc.hasNext("}")) {
							String line3 = sc.nextLine();
							this.addProduction(sym, line3);
						}
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getStartSymbol() {
		return this.start;
	}

	public void setStartSymbol(String sym) {
		this.start = sym;
	}

	public void addProduction(String nonTerminal, String prod) { 
		if (this.productions != null) {
			Map<String, List<String>> prods = this.productions;
			if (prods.containsKey(nonTerminal)) {
				List<String> list = prods.get(nonTerminal);
				list.add(prod);
				this.productions.put(nonTerminal, list);
			}
			else {
				List<String> list = new ArrayList<String>();
				list.add(prod);
				this.productions.put(nonTerminal, list);
			}
		}
		else {
			this.productions = new HashMap<String, List<String>>();
			this.addProduction(nonTerminal, prod);
		}
	}

	public List<String> getProductionList(String nonTerminal) {
		List<String> list = this.productions.get(nonTerminal);
		return list;
	}

	public String getRandProduction(String nonTerminal) {
		List<String> list = this.productions.get(nonTerminal);
		int size = list.size();
		Random generator = new Random();
		int randomIndex = generator.nextInt(size);
		String randomProd = list.get(randomIndex);
		return randomProd;
	}

	public Map<String, List<String>> getProds() {
		return this.productions;
	}
}


