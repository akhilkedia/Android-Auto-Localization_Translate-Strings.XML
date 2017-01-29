package main;

public class Const {
	// Enter your strings.xml file path here
	// ex: "C:/dev/AndroidStudioProjects/Catalog/app/src/main/res/values/strings.xml"	
	public static final String STRINGS_XML_FILE_PATH = "E:\\Softwares\\Android Studio\\StudioProjects\\AllTrans\\app\\src\\main\\res\\values\\strings.xml";
	
	//Original language of the above strings.xml
	public static final String ORIGINAL_LANGUAGE = "en";
	
	//Enter Microsoft Azure Translate API key here
	//go to www.portal.azure.com to get your FREE key
	public static final String API_KEY = "";
	
	//Folder to cache the translations, helping you save on your free quota limit!
	public static final String CACHE_PATH = "E:\\Softwares\\AutoTrans\\cache\\";
	
	//Path where to put all generated values**/string.xml
	public static final String DESTINATION_PATH = "E:\\Softwares\\Android Studio\\StudioProjects\\AllTrans\\app\\src\\main\\res\\"; // final folder address
	
	
	//DO NOT EDIT THE FOLLOWING unless you know what you are doing.
	public static final String BING_SUPPORTED_LANGUAGES_URL = "https://dev.microsofttranslator.com/languages?api-version=1.0&scope=text";
	public static final String BING_TOKEN_ADRESS = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
	public static final String TRANSLATE_URL = "https://api.microsofttranslator.com/v2/http.svc/Translate?";


}
