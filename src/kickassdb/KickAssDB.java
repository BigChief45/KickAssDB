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
        
        String path = "src\\kickassdb\\lexer.flex";
        generateLexer(path);
        generarCup();
        
        System.out.println("Prueba");
        
        MainWindow m = new MainWindow();
        m.setLocationRelativeTo(null);
        m.setVisible((true));
        
    }//End public static void main(String[] args)
    
    private static void generateLexer(String path)
    {
        File f = new File(path);
        jflex.Main.generate(f);
    }
    
    private static void generarCup()
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
            System.out.println("Compilando archivo .cup ... \n");
            java_cup.Main.main(opciones);
            System.out.println("archivo .cup compilado exitosamente. \n");

        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
}//End public class KickAssDB
