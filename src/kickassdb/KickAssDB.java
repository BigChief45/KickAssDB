/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kickassdb;

import java.io.File;


/**
 *
 * @author Otto
 */
public class KickAssDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String path = "C:/Users/Jaime Andres/Documents/NetBeansProjects/KickAssDB/src/kickassdb/Lexer.flex";
        generateLexer(path);
        
        MainWindow m = new MainWindow();
        m.setLocationRelativeTo(null);
        m.setVisible((true));
        
    }//End public static void main(String[] args)
    
    private static void generateLexer(String path)
    {
        File f = new File(path);
        jflex.Main.generate(f);
    }
}//End public class KickAssDB
