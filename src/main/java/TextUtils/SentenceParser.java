package TextUtils;

import java.io.*;
import java.util.ArrayList;

public class SentenceParser {

private ArrayList<String> abbr = new ArrayList<String>();

public SentenceParser() throws Exception {
    	BufferedReader br = new BufferedReader(new FileReader("data/abbrEngl.txt"));
		br.lines().forEach(line -> abbr.add(line));
        br.close();
}

public StringBuffer parseDoc(String input, String output) throws Exception {
    	BufferedReader br = new BufferedReader(new FileReader(input));
    	StringBuffer sb = new StringBuffer();
		parseText(br, sb);
		br.close();
        return sb;
     }

	private void parseText(BufferedReader br, StringBuffer sb) throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			String[] splitline = line.split(" ",-1);
			for (String word : splitline) {
				word = readEndOfSentences(word);
				addLineSeparators(sb, word);
			}
		}
	}

	private void addLineSeparators(StringBuffer sb, String word) {
		if (word.length() > 1 && !word.endsWith(".") && !word.endsWith("!") && !word.endsWith("?") && !abbr.contains(word)) {
			sb.append(word).append(" ");
		} else if (word.length() > 1 && !abbr.contains(word)) {
			sb.append(word).append(System.getProperty("line.separator"));
		} else if (word.length() > 0) {
			sb.append(word).append(" ");
		}
	}

	private String readEndOfSentences(String word) {
		if (word.startsWith("\'") || word.startsWith("\"")) {
			word = word.substring(1);
		}
		if (word.endsWith("\'") || word.endsWith("\"")) {
		   word = word.substring(0, word.length() - 1);
	   }
		if (word.endsWith("\'.") || word.endsWith("\".")) {
		   word = word.substring(0, word.length() - 2);
		   word = word + ".";
	   }
		if (word.endsWith("\'!") || word.endsWith("\"!")) {
		   word = word.substring(0, word.length() - 2);
		   word = word + "!";
	   }
		if (word.endsWith("\'?") || word.endsWith("\"?")) {
		   word = word.substring(0, word.length() - 2);
		   word = word + "?";
	   }
		return word;
	}

}

}