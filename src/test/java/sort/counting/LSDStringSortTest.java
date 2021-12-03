package sort.counting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.PinyinHelper;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static sort.huskySort.util.Utilities.isSorted;

public class LSDStringSortTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sort() throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        String encoding="UTF-8";
        File file=new File("src/main/resources/shuffledChinese.txt");
         if(file.isFile() && file.exists()){ //判断文件是否存在
             InputStreamReader read = new InputStreamReader(
                     new FileInputStream(file),encoding);//考虑到编码格式
             BufferedReader bufferedReader = new BufferedReader(read);
             String lineTxt = null;
             while((lineTxt = bufferedReader.readLine()) != null){
                arrayList.add(lineTxt);
             }
             read.close();
          }else{
             System.out.println("找不到指定的文件");
          }
        String[] strary = arrayList.toArray(new String[0]);
        LSDStringSort lsdStringSort = new LSDStringSort();
        lsdStringSort.sort(strary);
        assertTrue(PinyinHelper.isSorted(strary));
    }
}