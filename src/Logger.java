
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	static final String path = "H:/log.txt";

	
	public static void log(String a) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					StringBuilder b = new StringBuilder();
					FileReader inpt = new FileReader(path);
					BufferedReader reader = new BufferedReader(inpt);
					String line = reader.readLine();
					while (line != null) {
						b.append(line+"\n");
						line = reader.readLine();
					}
					b.append(a);
					FileWriter output = new FileWriter(path);
					BufferedWriter writer = new BufferedWriter(output);
					writer.write(b.toString());
					writer.close();
					output.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}}).run();
		}
}
