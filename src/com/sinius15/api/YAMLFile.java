package com.sinius15.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
 
/**
 * @author Sinius15
 * @see www.sinius15.com
 */
public class YAMLFile {
 
    private HashMap<String, String> data = new HashMap<String, String>();
    private boolean showError = false;
     
    /**
     * @param if errors are printed to System.out
     */
    public YAMLFile(boolean showError){
        this.showError = showError;
    }
     
    public void Save(File f) throws Exception{
         
        addEmptyThings();
         
        PrintWriter writer = new PrintWriter(f);
         
        SortedSet<String> keys = new TreeSet<String>(data.keySet());
        for (String path : keys) {
            String value = data.get(path);
            int level = getDotAmount(path);
            String builder = getSpaceString(level*4);
             
            String[] s = path.split("\\.");
            builder = builder + s[s.length-1] + ": " + value;
            writer.println(builder);
        }
        writer.close();
    }
     
    private void addEmptyThings(){
        SortedSet<String> keys = new TreeSet<String>(data.keySet());
        for (String path : keys) {
            while(true){
                path = deleteLastLevel(path);
                if(path == null || path.equals("")) {
                    break;
                }
                if(data.get(path) == null){
                    data.put(path, " ");
                }
            }
        }
    }
     
    public void Load(File file){
        data.clear();
        String[] levelName = new String[100];
         
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String[] p;
            while((line =  reader.readLine()) != null){
                if(line.equals("")) {
                    continue;
                }
                if(line.startsWith("#")) {
                    continue;
                }
                 
                p = line.split(":", 2);
                 
                int count = 0;
                for(int i = 0; i < p[0].length(); i++) {
                     if(Character.isWhitespace(p[0].charAt(i))) {
                        count++;
                    }
                }
                int level = count / 4;
                 
                String key = p[0].replace(" ", "").replace(":", "");
                 
                levelName[level] = key;
                 
                if(p.length == 1)
                    continue;
                 
                 
                if(!(p[1] == null || p[1].replaceAll(" ", "").equals(""))){
                     
                    String builder = "";
                    for(int i = 0; i <level; i++){
                        builder = builder + levelName[i] + ".";
                    }
                    builder = builder + key;
                     
                    String value = p[1].substring(1, p[1].length());
                    data.put(builder, value);
                }
                 
            }
 
             reader.close(); 
        }catch(Exception e){
            if(showError){
                System.err.println("YAML ERROR: You asked me to load a yml file, but i failed. I am sorry.");
                e.printStackTrace();
            }
        }
    }
     
    /**
     * this function is meant for files inside a .jar file.
     *
     */
    public void Load(URL file){
        data.clear();
        String[] levelName = new String[100];
         
        try{
            InputStream is = file.openStream();
            InputStreamReader isr = new InputStreamReader(is, "US-ASCII" );
            BufferedReader reader = new BufferedReader(isr);
             
            String line;
            String[] p;
            while((line =  reader.readLine()) != null){
                if(line.equals(""))
                    continue;
                 
                if(line.startsWith("#"))
                    continue;
                                 
                p = line.split(":", 2);
                 
                int count = 0;
                for(int i = 0; i < p[0].length(); i++) {
                     if(Character.isWhitespace(p[0].charAt(i))) {
                        count++;
                    }
                }
                int level = count / 4;
                 
                String key = p[0].replace(" ", "").replace(":", "");
                levelName[level] = key;
                 
                if(p.length == 1)
                    continue;
                 
                 
                if(!(p[1] == null || p[1].replaceAll(" ", "").equals(""))){
                     
                    String builder = "";
                    for(int i = 0; i <level; i++){
                        builder = builder + levelName[i] + ".";
                    }
                    builder = builder + key;
                     
                    String value = p[1].substring(1, p[1].length());
                    data.put(builder, value);
                }
                 
            }
 
            reader.close();
            isr.close();
            is.close();
        }catch(Exception e){
            if(showError){
                System.err.println("YAML ERROR: You asked me to load a yml file, but i failed. I am sorry.");
                e.printStackTrace();
            }
        }
    }
     
     
    @SuppressWarnings("unused")
    private static String getLevelName(String path, int level){
        String[] s = path.split("\\.");
        String builder = "";
        for(int i = 0; i<level; i++){
            builder = builder + s[i] + ".";
        }
        if(builder.equals("")) return null;
        builder = builder.substring(0, builder.length()-1);
        return builder;
    }
     
    private static String getSpaceString(int amount){
        String builder = "";
        for(int i = 0; i< amount; i++){
            builder = builder + " ";
        }
        return builder;
    }
     
    private static int getDotAmount(String path){
        return path.length() - path.replaceAll("\\.", "").length();
    }
     
    private static String deleteLastLevel(String path){
        String[] s = path.split("\\.");
        if(s.length == 1)
            return null;
         
        String builder = "";
        for(int i = 0; i <s.length-1; i++){
            builder = builder + s[i] + ".";
        }
        if(builder.equals(""))  return null;
         
        builder = builder.substring(0, builder.length()-1);
        return builder;
    }
     
    public void addString(String path, String value){
        data.put(path, "\"" + value + "\"");
    }
     
     
     
     
     
     
    //getters and setters for variables
     
    public String getString(String path){
        String s;
        if((s = data.get(path)) == null)
            return null;
        return s.replaceAll("\"", "");
    }
     
    public void addInt(String path, int value){
        data.put(path, String.valueOf(value));
    }
     
    public Integer getInt(String path){
        String s;
        if((s = data.get(path)) == null)
            return null;
        int i = 0;
        try{
            i = Integer.valueOf(s);
        }catch(Exception e){
            if(showError) {
                System.err.println("YAML ERROR: You asked for a int, but there was an other object(like a string) stored.  path: " + path);
            }
            return null;
        }
        return i;
    }
     
    public void addboolean(String path, boolean value){
        data.put(path, String.valueOf(value));
    }
     
    public Boolean getboolean(String path){
        String s;
        if((s = data.get(path)) == null)
            return null;
        if(s.equals("true"))
            return true;
        if(s.equals("false"))
            return false;
        if(showError) {
            System.err.println("YAML ERROR: You asked for a boolean, but there was an other object(like a string) stored.  path: " + path);
        }
        return null;
             
         
    }
     
}