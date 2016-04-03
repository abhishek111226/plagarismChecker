import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFileChooser;


public class mymain 
{
	static HashMap<String,Vector<String>> adjacency_list = new HashMap<String,Vector<String>>();
	public static void main(String args[])
	{
		try {
			accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void accept() throws IOException
	{
		JFileChooser fc= new JFileChooser();
	    int ret = fc.showOpenDialog(null);
	    if (ret== JFileChooser.CANCEL_OPTION) 
	        return;
	    
	    File selectedFile = fc.getSelectedFile();
	    String filename = selectedFile.getName();
	    File outfile = new File(filename+"_bb");
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(selectedFile));
        BufferedWriter brout = null;
        brout = new BufferedWriter(new FileWriter(outfile));
        String line;
        String entry=null;
        while((line = br.readLine()) != null)
        {
        	if(!line.isEmpty() && !line.contains("->"))
        	{
        		brout.write(line);
        		brout.write("\n");
        		/*if(line.contains("entry"))
        		{
        			String split[]=line.split("[");
        			entry = split[0];
        		}*/
        	}
        	else if(!line.isEmpty())
        	{
        		System.out.println("" + line);
        		String split[] = line.split("->");
        		if(split[0].contains(":"))
        		{
        			/* Remove later part from : take only left part	*/
        			split[0]=split[0].split(":")[0];
        		}
        		else if(split[1].contains(":"))
        		{
        			split[1]=split[1].split(":")[0];
        		}
        		if(adjacency_list.size()==0)
        		{
        			entry=split[0];
        		}
        		/* Add the edge	*/
        		if(!adjacency_list.containsKey(split[0]))
        		{
        			adjacency_list.put(split[0],new Vector<String>());
        		}
        		adjacency_list.get(split[0]).add(split[1]);
        	
        	}
        }
        adjacency_list.put("PROGRAM_ENTRY",new Vector<String>());
		adjacency_list.get("PROGRAM_ENTRY").add(entry);
        print();
        br.close();
        brout.close();
	}
	static void print()
	{
		for ( String source : adjacency_list.keySet()) 
		{
			System.out.println("-----------------------");
			System.out.println("Edges from "+source+" :");
			for (String destination : adjacency_list.get(source)) 
			{
				System.out.println(" "+source+" -> "+destination);
			}
			System.out.println("-----------------------");
		}
	}
	void adjacency_in_file()
	{
		for ( String source : adjacency_list.keySet()) 
		{
			System.out.println("-----------------------");
			System.out.println("Edges from "+source+" :");
			for (String destination : adjacency_list.get(source)) 
			{
				System.out.println(" "+source+" -> "+destination);
			}
			System.out.println("-----------------------");
		}
	}
}
