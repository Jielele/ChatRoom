import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
public class swap {
    public static void main(String[] args) {

        replaceTxtByStr("D:\\text.txt", "万佳乐 123456", "万佳乐 1234567");

        iteratorDirectory("D:\\text.txt", "$file$", "test_000");

    }
    public static void iteratorDirectory(String fileDir, String oldStr, String replaceStr) {

        File file = new File(fileDir);

        if (file.isDirectory()) {

            String[] fileList = file.list();

            for (int i = 0; i < fileList.length; i++) {

                iteratorDirectory(fileDir + File.separator + fileList[i], oldStr, replaceStr);

            }

        } else {

            replaceTxtByStr(fileDir, oldStr, replaceStr);

        }

    }

    /**

     * 替换文件中的字符串

     *

     * @param filePath

     * @param oldStr

     * @param replaceStr

     */

    public static void replaceTxtByStr(String filePath, String oldStr, String replaceStr) {

        String temp = "";

        int len = oldStr.length();

        StringBuffer tempBuf = new StringBuffer();

        try {

            File file = new File(filePath);

            FileInputStream fis = new FileInputStream(file);

            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);

            StringBuffer buf = new StringBuffer();

// 按行读取，仅替换每行第一个匹配的字符串

//while ((temp = br.readLine()) != null) {

// if (temp.contains(oldStr)) {

// int index = temp.indexOf(oldStr);

// tempBuf.append(temp);

// tempBuf.replace(index, index + len, replaceStr);

// buf.append(tempBuf);

// tempBuf.setLength(0);

// } else {

// buf.append(temp);

// }

// buf = buf.append(System.getProperty("line.separator"));

// }

// 替换所有匹配的字符串

            for (temp = null; (temp = br.readLine()) != null; temp = null) {

                if (temp.indexOf(oldStr) != -1) {

                    temp = temp.replace(oldStr, replaceStr);

                }

                buf.append(temp);

                buf.append(System.getProperty("line.separator"));

            }

            br.close();

            FileOutputStream fos = new FileOutputStream(file);

            PrintWriter pw = new PrintWriter(fos);

            pw.write(buf.toString().toCharArray());

            pw.flush();

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}