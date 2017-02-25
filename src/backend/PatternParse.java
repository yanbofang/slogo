package backend;

/***
 * CODE BASED OFF OF CODE PRESENTED BY ROBERT DUVALL IN DUKE'S CS308 LECTURE
 */
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class PatternParse {

	private List<Entry<String, Pattern>> mySymbols;
	private List<Entry<Pattern, String>> mySymbolsRegex;

	public PatternParse() {
		mySymbols = new ArrayList<Entry<String, Pattern>>();
		mySymbolsRegex = new ArrayList<Entry<Pattern, String>>();
	}

	/**
	 * If the key of the properties file is in regular expression, the boolean keyIsRegex will be true, vice versa.
	 * @param pattern
	 * @param keyIsRegex
	 */
	public void addPattern(String pattern, boolean keyIsRegex) {
		ResourceBundle resource = ResourceBundle.getBundle(pattern);
		Enumeration<String> iterator = resource.getKeys();
		while (iterator.hasMoreElements()) {
			String key = iterator.nextElement();
			String value = resource.getString(key);
			if (keyIsRegex) {
				mySymbolsRegex.add(new SimpleEntry<>(Pattern.compile(key, Pattern.CASE_INSENSITIVE), value));
			} else {
				mySymbols.add(new SimpleEntry<>(key, Pattern.compile(value, Pattern.CASE_INSENSITIVE)));
			}
		}
	}

	public String getSymbol(String text, boolean keyIsRegex) {
		final String ERROR = "NO MATCH";
		if(keyIsRegex){
			for (Entry<Pattern, String> e : mySymbolsRegex) {
				if (e.getKey().matcher(text).matches()) {
					return e.getValue();
				}
			}
		}else{
			for (Entry<String, Pattern> e : mySymbols) {
				if (e.getValue().matcher(text).matches()) {
					return e.getKey();
				}
			}
		}
		return ERROR;
	}
}
