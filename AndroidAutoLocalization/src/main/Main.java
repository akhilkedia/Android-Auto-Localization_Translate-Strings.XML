package main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.naming.InitialContext;
import javax.net.ssl.HttpsURLConnection;

public class Main {
	// Please fill all Constants in Const.java
	
	public static void main(String[] args) throws Exception {
		StringsFilesGenerator generator = new StringsFilesGenerator();
		generator.execute();
	}

}