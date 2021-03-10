package main;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class Util {

	public static String splitXmlResult(String word) {
		// translate result returns like
		// <string
		// xmlns="http://schemas.microsoft.com/2003/10/Serialization/">Katalogus</string>
		System.out.println("Response - " + word);
		int beginIndex = StringUtils.indexOf(word,
				"<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">") + 68;
		int endIndex = StringUtils.indexOf(word, "</string");
		try {
			return word.substring(beginIndex, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> removeUnsupported(Map<String, String> abbNamePair) {
		// there are some country codes that are not same with google.
		// we exchange these country codes with google equivalents
		Iterator<String> iter = abbNamePair.keySet().iterator();
		while (iter.hasNext()) {
			String language = iter.next();
			if (language.equals("fjn") 
					|| language.equals("fr-CA")
					|| language.equals("kmr")
					|| language.equals("mww")
					|| language.equals("otq")
					|| language.equals("prs")
					|| language.equals("pt-pt") 
					|| language.equals("pt-PT")
					|| language.equals("sm") 
					|| language.equals("sr-Latn")
					|| language.equals("tlh-Latn") 
					|| language.equals("tlh-Piqd")
					|| language.equals("ty") 
					|| language.equals("yua")
					|| language.equals("zh-Hant") ) {
				iter.remove();
			}
		}
		return abbNamePair;
	}

	public static String exchangeProblematicCountryCode(String code) {
		// there are some country codes that are not same with google.
		// we exchange these country codes with google equivalents
		String result = code;

		if (code.equals("he")) {
			result = "iw";
		} else if (code.equals("id")) {
			result = "in";
		} else if (code.equals("sr-Cyrl")) {
			result = "sr";
		} else if (code.equals("zh-Hans")) {
			result = "zh";
		}
		return result;
	}

	public static String getXmlFormattedLine(String word, String tag) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\t");
		buffer.append("<string name=\"");
		// buffer.append(XMLEscape(tag));
		buffer.append(tag);
		buffer.append("\">\"");
		buffer.append(StringEscapeUtils.escapeXml11(word));
		buffer.append("\"</string>");
		return buffer.toString();
	}

	public static String XMLUnescape(String s) {
		String retVal = s.replaceAll("&amp;", "&");
		retVal = retVal.replaceAll("&quot;", "\"");
		retVal = retVal.replaceAll("&apos;", "'");
		retVal = retVal.replaceAll("&lt;", "<");
		retVal = retVal.replaceAll("&gt;", ">");
		retVal = retVal.replaceAll("&#xD;", "\r");
		retVal = retVal.replaceAll("&#xA;", "\n");
		return retVal;
	}

	public static String fixnewlines(String s) {
		s = s.replaceAll("&#xD;", "\r\n");
		return s;
	}

}
