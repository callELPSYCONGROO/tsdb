package com.bici.tsdb.common.util;

import java.io.*;

/**
 * FileUtil
 * @author: Overload
 * @date: 2018/5/3 10:44
 * @version: 1.0
 */
public class FileUtil {

    private static File success;
    private static File fail;
    private static File time;

    static {
        success = new File("./success.log");
        fail = new File("./fail.log");
        time = new File("./time.log");
    }

    public static void write(int t, long pt) {
        Long i;
        try (InputStream is = new FileInputStream(getFile(t))){
            byte[] bytes = new byte[is.available()];
            StringBuilder sb = new StringBuilder();
            while (is.read(bytes) > 0) {
                sb.append(new String(bytes));
            }
            i = Long.valueOf(sb.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        try (OutputStream os = new FileOutputStream(getFile(t))) {
            os.write(String.valueOf(t == 3 ? (i + pt) : (i + 1)).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(int t) {
        try (InputStream is = new FileInputStream(getFile(t))) {
            byte[] bytes = new byte[is.available()];
            StringBuilder sb = new StringBuilder();
            while (is.read(bytes) > 0) {
                sb.append(new String(bytes));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File getFile(int t) {
        return t == 1 ? success : t == 2 ? fail : time;
    }
}
