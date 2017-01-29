package object;

import main.HTTPRequestHandler;

public class ThreadPool {

	Thread thread;
	String fromWord;
	String resultWord;
	String attr;

	HTTPRequestHandler handler;

	public ThreadPool(final HTTPRequestHandler handler, final String fromLang,
			final String toLang, final String fromWord, String attr) {
		super();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				if (toLang.equalsIgnoreCase("default")) {
					resultWord = fromWord;
				} else {
					resultWord = handler.getTranslation(fromLang, toLang,
							fromWord);
//					resultWord = Double.toString((Math.random() * 10));
				}
			}
		});
		this.attr = attr;
		this.fromWord = fromWord;
	}

	public void run() {
		thread.start();
	}

	public String getResultWord() {
		return resultWord;
	}

	public String getFromWord() {
		return fromWord;
	}

	public void setResultWord(String resultWord) {
		this.resultWord = resultWord;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isAlive() {
		return thread.isAlive();
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

}
