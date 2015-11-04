package com.imooc.web.tools;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class CodeCounter {

    //有效行数
    private static long normalLines = 0;
    
    //注释行数
    private static long commentLines = 0;
    
    //空白�?
    private static long whiteLines = 0;

    private ArrayList<File> al = new ArrayList<File>();
    
    private static BufferedWriter output;

    /**
     * 递归找到该目录下的所有java源文�?
     * @param homeDir
     */
    public void findJavaSource(File homeDir) {
        if(homeDir.isFile()){
            al.add(homeDir);
            return;
        }
        File[] f = homeDir.listFiles();
        for (File child : f) {
            if (child.isFile() && (child.getName().matches(".*\\.java$"))) {
                al.add(child);
            } else if (child.isDirectory()) {
                findJavaSource(child);
            }
        }
    }

    public static void main(String []str) throws IOException{
    	String[] projects = new String[]{
    			"base",
    			"basedata",
    			"basedata-web",
    			"blog",
    			"blog-web",
    			"broker",
    			"broker-web",
    			"build",
    			"fastsale",
    			"fastsale-web",
    			"framework",
    			"houseproject",
    			"houseproject-web",
    			"hr",
    			"hr-web",
    			"interflow",
    			"interflow-web",
    			"log",
    			"log-web",
    			"meal",
    			"parent",
    			"settlement",
    			"settlement-web",
    			"trade",
    			"trade-web",
    			"transfer",
    			"transfer-web",
    			"web",
    			"workbench",
    			"workflow",
    			"workflow-web"
    	};
    	for(String s : projects){
    		System.out.println(s);
//	    	output = new BufferedWriter(new FileWriter(new File("F:/source/"+s+".txt")));
	        CodeCounter cc = new CodeCounter();
	        //测试时别忘了该目录地�?
	        File homeDir = new File("F:\\workspace\\"+s);
	        cc.count(homeDir);
//	        output.close();
    	}
    }
    
//    public static void main(String[] args) throws IOException {
//    	CodeCounter cc = new CodeCounter();
//        //测试时别忘了该目录地�?
//        File homeDir = new File("F:\\workspace\\base");
//        cc.count(homeDir);
//	}
//    
    
    public void count(File homeDir) throws IOException {
        this.findJavaSource(homeDir);
        for (File child : al) {
            parse(child);
        }
        System.out.println("有效行数:" + normalLines);
        System.out.println("注释�?:" + commentLines);
        System.out.println("空白�?:" + whiteLines);
    }

    private static void parse(File f) throws IOException {
    	if(output!=null){
    		output.append(f.getName()).append("\n");
    	}
        BufferedReader br = null;
        boolean comment = false;
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = br.readLine()) != null) {
            	if(output!=null){
            		output.append(line).append("\n");
            	}
                line = line.trim();
                if (line.matches("^[\\s&&[^\\n]]*$")) {
                    whiteLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    comment = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (true == comment) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        comment = false;
                    }
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else {
                    normalLines++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}