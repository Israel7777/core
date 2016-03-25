/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author natnael
 */
public class Load {
   static Load load = new Load();
   
   private static final String ACCOUNSTPATH = "./src/mygame/util/account";
   private static final String CURRICULUMSTPATH = "./src/mygame/util/curriculum";
   
   
    public void main(String[] args){
        File[] files = load.loadAccounts();
        if(files.length == 0){
            System.out.println("no .txt file");
        }     
        else{
            for(File file : files){
                System.out.println(file.getName());
            }
        }
        files = load.loadCurriculums();
        if(files.length == 0){
            System.out.println("no .txt file");
        }     
        else{
            for(File file : files){
                System.out.println(file.getName());
            }
        }
        System.out.println(getFile(ACCOUNSTPATH,"abebe").toString());
    }
    
    public static File getFile(String directory,String fileName){
        File file = null;
        File dir = new File(directory);
        File[] matches=null;
        if(!dir.exists())
            System.out.println("dir don't exist");
        else{
              System.out.println("finding file");

              matches = dir.listFiles(new FilenameFilter(){
                public boolean accept(File dir, String name){
                   return name.startsWith(name) && name.endsWith(".obj");
                }
              });
              
        }
        //System.out.println("file found");
        file = matches[0];
        return file;
    }
    
    public File[] loadAccounts(){
        
        File dir = new File(ACCOUNSTPATH);
        File[] matches=null;
        if(!dir.exists())
            System.out.println("dir don't exist");
        else{
              matches = dir.listFiles(new FilenameFilter(){
              public boolean accept(File dir, String name)
              {
                 return name.endsWith(".obj");
              }
            });
        }
        
        return matches;
    }
    
    public File[] loadCurriculums(){
        
        File dir = new File(CURRICULUMSTPATH);
        File[] matches=null;
        if(!dir.exists())
            System.out.println("dir don't exist");
        else{
              matches = dir.listFiles(new FilenameFilter(){
              public boolean accept(File dir, String name)
              {
                 return name.endsWith(".cu");
              }
            });
        }
        
        return matches;
    }
}
