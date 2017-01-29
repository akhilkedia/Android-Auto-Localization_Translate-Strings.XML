package object;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


public class GsonResponse {
//	String text;
	Map<String, Language> text = new LinkedHashMap<String, GsonResponse.Language>();
	
	
	
	public Map<String, Language> getText() {
		return text;
	}



	public void setText(LinkedHashMap<String, Language> text) {
		this.text = text;
	}



	public class Language {
		String name;
		String dir;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDir() {
			return dir;
		}
		public void setDir(String dir) {
			this.dir = dir;
		}
		
		
	}
}
