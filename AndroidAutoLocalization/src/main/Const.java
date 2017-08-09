package main;

public class Const {
	// Enter your strings.xml file path here
	// ex:
	// "C:/dev/AndroidStudioProjects/Catalog/app/src/main/res/values/strings.xml"
	public static final String STRINGS_XML_FILE_PATH = "C:\\Software\\Android\\StudioProjects\\AllTransOtherFiles\\AutoTrans\\res\\values\\strings.xml";

	// Optional - Enter your html file path here
	// ex: "C:/dev/AndroidStudioProjects/Catalog/app/src/main/res/raw/readme.html"
	// If you dont want to translate HTML, set the next boolean to false.
	public static final String HTML_FILE_PATH = "C:\\Software\\Android\\StudioProjects\\AllTransOtherFiles\\AutoTrans\\res\\raw\\readme.html";

	// Set this boolean to false to not translate HTML
	public static final boolean TRANSLATE_HTML = true;

	// Original language of the above strings.xml
	public static final String ORIGINAL_LANGUAGE = "en";

	// Enter Microsoft Azure Translate API key here
	// go to www.portal.azure.com to get your FREE key
	public static final String API_KEY = "";

	// Folder to cache the translations, helping you save on your free quota limit!
	public static final String CACHE_PATH = "C:\\Software\\Android\\StudioProjects\\AllTransOtherFiles\\AutoTrans\\cache\\";

	// Path where to put all generated values**/string.xml
	public static final String DESTINATION_PATH = "C:\\Software\\Android\\StudioProjects\\AllTransOtherFiles\\AutoTrans\\genvalues\\"; // final
																																		// folder
																																		// address

	// HERE BE DRAGONS
	// DO NOT EDIT THE FOLLOWING unless you know what you are doing.
	public static final String BING_SUPPORTED_LANGUAGES_URL = "https://dev.microsofttranslator.com/languages?api-version=1.0&scope=text";
	public static final String BING_TOKEN_ADRESS = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
	public static final String TRANSLATE_URL = "https://api.microsofttranslator.com/v2/http.svc/Translate?";

}
