/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kickassdb;

import java.io.File;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

/**
 *
 * @author Otto
 */
public class KickAssDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    UIManager.setLookAndFeel(info.getClassName());                    
                    break;
                }
            }
        } 
        catch (Exception e) 
        {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
        System.out.println("Prueba");
        
        MainWindow m = new MainWindow();
        m.setLocationRelativeTo(null);
        m.setVisible((true));
        
    }//End public static void main(String[] args)
    
    protected static void generateLexer(String path)
    {
        System.out.println("Compiling Lexer file... \n");
        File f = new File(path);
        jflex.Main.generate(f);
        System.out.println("Lexer file compiled successfully.");
    }
    
    protected static void generateCup()
    {
        String opciones[] = new String[5];
        opciones[0] = "-destdir";
        opciones[1] = "src\\kickassdb\\";
        opciones[2] = "-parser";
        opciones[3] = "parser";
        opciones[4] = "src\\kickassdb\\parser.cup";

        try
        {
            System.out.println(""); // Clear
            System.out.println("Compiling CUP file... \n");
            java_cup.Main.main(opciones);
            System.out.println("CUP file compiled successfully. \n");

        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
}//End public class KickAssDB
