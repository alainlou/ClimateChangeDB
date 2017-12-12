package lou.alain;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.HashSet;
import twitter4j.User;

public class TweetParser {
	private PrintWriter pw;
	private HashMap<String, Integer> freq;
	private HashSet<Integer> dn;
	TweetParser(String filename) throws FileNotFoundException, UnsupportedEncodingException{
		pw = new PrintWriter(filename + ".txt", "UTF-8");
		freq = new HashMap<String, Integer>();
		dn = new HashSet<Integer>();
	}
	public void add(long id, String location){
		if(dn.contains(id)){
			System.out.println("dupe");
			return;
		}
		freq.putIfAbsent(location, 0);
		freq.put(location, freq.get(location)+1);
	}
	public void add(User s) throws java.lang.ArrayIndexOutOfBoundsException{
		add(s.getId(), s.getLocation());
	}
	public void close(){
		freq.forEach((k,v) -> pw.println("location: "+k+" frequency: "+v));
		pw.close();
	}

}
