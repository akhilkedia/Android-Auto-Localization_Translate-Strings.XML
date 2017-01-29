package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import object.ThreadPool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StringsFilesGenerator {

	// List<String> wordList = new ArrayList<String>();
	Map<String, String> tagValueMap = new LinkedHashMap<String, String>(); /* tag == attribute name, value = word to be translated */
	private Map<String, String> abbNamePair = new LinkedHashMap<String, String>();
	HTTPRequestHandler handler;

	public void execute() {
		handler = new HTTPRequestHandler();
		readStringsFile();
		getLanguageList();
		abbNamePair = Util.removeUnsupported(abbNamePair);
		getTranslations();
	}

	@SuppressWarnings("unchecked")
	private void getTranslations() {
		try {
			Map<String, String> wordList = new LinkedHashMap<String, String>(); /* tag == attribute name, value = word to be translated */
			wordList.putAll(this.tagValueMap);
			abbNamePair.remove("en");
//			abbNamePair.clear();
//			abbNamePair.put("default", "English");
//			abbNamePair.put("de", "de");
//			abbNamePair.put("fa", "fa");
//			abbNamePair.put("fr", "fr");
//			abbNamePair.put("ja", "ja");
//			abbNamePair.put("ko", "ko");
//			abbNamePair.put("ru", "ru");
//			abbNamePair.put("sv", "sv");
//			abbNamePair.put("th", "th");
//			abbNamePair.put("vi", "vi");
//			abbNamePair.put("hi", "hi");
			// File file = new File("C:\\Users\\Lenovo\\Desktop\\test");
			// file.mkdir();
			for (String language : abbNamePair.keySet()) {
				File file2;
               
				HashMap<String, String> cache = new HashMap<String, String>();
				String cachePath = Const.CACHE_PATH +language;
				
				File f = new File(cachePath);
				if(!f.getParentFile().exists())
					f.getParentFile().mkdirs();
				try{
				if(f.exists() && !f.isDirectory()) {
					FileInputStream fileInputStream = new FileInputStream(f);
					ObjectInputStream s = new ObjectInputStream(fileInputStream);
					cache = (HashMap<String, String>) s.readObject();
					s.close();
				}
				}catch(Exception e){
					cache = new HashMap<String, String>();
				}
                
//				if (language.equals("default")) {
//					file2 = new File(Const.DESTINATION_PATH
//							+ "values\\values\\strings.xml");
//				} else {
//				}		

				file2 = new File(Const.DESTINATION_PATH + "values-"
						+ Util.exchangeProblematicCountryCode(language) + "\\strings.xml");

				file2.getParentFile().mkdirs();
				file2.createNewFile();
				List<ThreadPool> threads = new ArrayList<>();
				System.out.println("-----------------"
						+ abbNamePair.get(language) + "-------------------");
				for (String attr : wordList.keySet()) {
					if(!cache.containsKey(wordList.get(attr))){
						threads.add(new ThreadPool(handler, "en", language,
								wordList.get(attr), attr));
					}
				}
				for (ThreadPool threadPool : threads) {
					threadPool.run();
				}
				boolean isAllDead = false;
				while (!isAllDead) {
					if(threads.size()==0)
						isAllDead = true;
					for (ThreadPool thread : threads) {
						if (thread.isAlive()) {
							isAllDead = false;
							break;
						} else {
							isAllDead = true;
						}
					}
					if (!isAllDead)
						Thread.sleep(200L);
				}
				PrintWriter writer = new PrintWriter(file2.getAbsolutePath(),
						"UTF-8");
				writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
				writer.println("<resources>");
				
				for (String attr : wordList.keySet()) {
					if(cache.containsKey(wordList.get(attr))){
						System.out.println(cache.get(wordList.get(attr)));
						writer.println(Util.getXmlFormattedLine(
								cache.get(wordList.get(attr)), attr));
					}
				}
				
				for (ThreadPool pool : threads) {
					//System.out.println(pool.getResultWord());
					cache.put(pool.getFromWord(), pool.getResultWord());
					writer.println(Util.getXmlFormattedLine(
							pool.getResultWord(), pool.getAttr()));
				}
				
				writer.print("</resources>");
				writer.close();
				threads.clear();
				
                FileOutputStream fileOutputStream = new FileOutputStream(cachePath);
                ObjectOutputStream s = new ObjectOutputStream(fileOutputStream);
                s.writeObject(cache);
                s.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getLanguageList() {
		try {
			abbNamePair = handler.getLanguageList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readStringsFile() {
		// Parse strings.xml file and system.out all words;
		try {

			File fXmlFile = new File(Const.STRINGS_XML_FILE_PATH);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("string");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				// System.out.println("\nCurrent Element :" +
				// nNode.getNodeName() + " value" + nNode.getNodeValue() + " " +
				// nNode.getFirstChild().getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String tag = eElement.getAttribute("name").toString();
					String value = Util.XMLUnescape(eElement.getTextContent().replace("\n", "\r\n"));
					value = value.replace("\"", "");
					System.out.println(tag + ", " + value);
					tagValueMap.put(tag, value);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
