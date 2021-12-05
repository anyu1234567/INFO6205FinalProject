package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputIntoFile {
    public static   void writeIntoFile(String[] strary,String fileName){
        try{
            String[] data= new String[999];
            System.arraycopy(strary,0,data,0,999);
            String path = "logs/"+fileName+".txt";
            File file =new File(path);

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file

            FileWriter fileWritter = new FileWriter(file.getPath());
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            for (String s: data){
                bufferWritter.write(s+"\n");
            }

            bufferWritter.close();



        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
