/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kickassdb;
import java.io.*;
import javax.swing.JOptionPane;


/**
 *
 * @author cindy
 */
public class AddFile {
    static private String readFile(String fileName) {
        String read = "";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                read += tempString + "\r\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        return read;
    }
    
    static public void AddNewFolder(String path_name) {
        try {
            File myFilePath = new File(path_name);  
            if (!myFilePath.exists()) {  
                myFilePath.mkdir();  
            }
            else {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Folder already existed!", "Error", JOptionPane.ERROR_MESSAGE);                                         
            }
        }  
        catch (Exception e) {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Folder creation error", "Error", JOptionPane.ERROR_MESSAGE);                                         
            e.printStackTrace();  
        }
    }
    
    /* turns the tuple to a string to write in the data.txt*/
    static public void AddData(String path_name, Tuple tuple) {
        String content = "";
        
        for(Value val : tuple.getTuple_values()) {
            content += val.getValue().toString() + " ";
        }
        
        AddToFile(path_name, content);
    }

    /* add some data to the data.txt */
    static public void AddToFile(String file_name, String file_content) {  
        try {
            File myFilePath = new File(file_name);
            file_content = readFile(file_name) + file_content;
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();  
            } 
            FileWriter resultFile = new FileWriter(myFilePath);  
            PrintWriter myFile = new PrintWriter(resultFile);  
            myFile.println(file_content);
            resultFile.close();
        }
        catch (Exception e) {  
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "File creation error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  
        }

    }
    
    /* create the attribute.txt file */
    public static void AddAttribute(String file_name, Table table) throws Exception {  
        File myFilePath = new File(file_name) ;
        if (!myFilePath.exists()) {
            myFilePath.createNewFile();  
        } 

        OutputStream out = new FileOutputStream(myFilePath);
        ObjectOutputStream oos = new ObjectOutputStream(out) ;  
        oos.writeObject(table);
        oos.close(); 
    }  
    
    /* to see if the information in the attibute.txt is right */
    public static void TestPrint(String file_name, Table table) throws Exception {  
        File f = new File(file_name);
        InputStream input = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(input);
        Table obj = (Table)ois.readObject();
        ois.close();
        String show_message = "Table name of the attibute.txt is: " + obj.getTable_name();
        System.out.println(show_message);
    }
    
    
}
