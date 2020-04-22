package Implementation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// use groupings
public final class InputParser {
	
	// 1. Use regular expresssions (Pattern) for validating input data
	//    U�y� regularnych wyra�e� (Pattern) do walidacji danych wej�ciowych
	//
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd" 
	//    Konwersj� wej�ciowego ci�gu znak�w reprezentuj�cego dat� nale�y oprze� np. DateFormat 
	//    SimpleDateFormat format "yyyy-MM-dd"


	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private static final String NAME_GROUP = "firstName";
	private static final String SURNAME_GROUP = "surname";
	private static final String BIRTHDATE_GROUP = "birthdate";
	private static final String NAME = "(?:[A-Z]([a-z])+)";
	private static final String NAME_PT = "(?<" + NAME_GROUP + ">" + NAME + ")";
	private static final String SURNAME_PT = "(?<" + SURNAME_GROUP +">" + NAME+")";
	private static final String YEAR = "(?:\\d{4})";
	private static final String MONTH = "(?:(?:[0]\\d)|(?:[1]\\d))";
	private static final String DAY = "(?:(?:[0]\\d)|(?:[1-2]\\d)|(?:[3][0-1]))";
	private static final String SEPARATOR = ".";

	private static final String BIRTHDATE_PT = "(?<" + BIRTHDATE_GROUP +">"+
			YEAR + SEPARATOR + MONTH + SEPARATOR + DAY + ")";

	private static final String WHITESPACE = "(?:[\\s\t]+)";

	private static final Pattern LINE_REGEX = Pattern.compile(NAME_PT + WHITESPACE + SURNAME_PT + WHITESPACE + BIRTHDATE_PT);

	public static List<Person> parse(File file) {
		BufferedReader in;
		List<Person> personList = new ArrayList<>();
		try{
			in = new BufferedReader(new InputStreamReader( new FileInputStream(file), StandardCharsets.UTF_8));
			String line;
			while((line = in.readLine()) != null){
				Person pers = parse(line);
				if(pers != null){
					personList.add(pers);
				}
			}

			in.close();

		} catch(IOException f ){ }


		return personList;
	}

	private static Person parse(String line){
		Matcher match = LINE_REGEX.matcher(line);
		if(!match.matches()) return null;
		String name = match.group(NAME_GROUP);
		String surname = match.group(SURNAME_GROUP);
		Date birthdate = null;
		try {
			birthdate = DATEFORMAT.parse(match.group(BIRTHDATE_GROUP));
		} catch (ParseException e) {
			return null;
		}
		return new Person(name,surname,birthdate);
	}
}