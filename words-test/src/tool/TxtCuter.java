package tool;

import java.io.*;
import java.util.LinkedList;

public class TxtCuter {
    String[] arrey;
    public TxtCuter(File file) throws IOException {
        String line="";
        LinkedList<String> words=new LinkedList<>();
        InputStream fis=new FileInputStream(file.getAbsolutePath());
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            for (String str:line.split("[. ,:\";~?!]"))
            {
                if (!str.equals(""))
                {
                    words.add(str);
                }
            }
        }
        System.out.println(words);
        arrey=(String[]) words.toArray(new String[0]);
        fis.close();
    }
    public String[] getArrey(){
        return arrey;
    }
}

