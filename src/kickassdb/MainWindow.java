/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kickassdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Otto
 */
public class MainWindow extends javax.swing.JFrame {

	private Hashtable attributes;
        private int table_counnter = 0;
        private File opened_file;
        String parserResult = "Parsing Successful";
                
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() 
    {
        initComponents();
                  
    }//End MainWindow()

    // Create some different font styles
    public void createStyles( StyleContext sc )
    {
            Style myStyle;

            // Allocate a hashtasble for our styles
            attributes = new Hashtable();

            // No style
            myStyle = sc.addStyle( null, null );
            attributes.put( "none", myStyle ); 

            // Normal
            myStyle = sc.addStyle( null, null );
            StyleConstants.setLeftIndent( myStyle, 10 );
            StyleConstants.setRightIndent( myStyle, 10 );
            StyleConstants.setFontFamily( myStyle, "Helvetica" );
            StyleConstants.setFontSize( myStyle, 14 );
            StyleConstants.setSpaceAbove( myStyle, 4 );
            StyleConstants.setSpaceBelow( myStyle, 4 );
            attributes.put( "normal", myStyle ); 

            // Big
            myStyle = sc.addStyle( null, null );
            StyleConstants.setFontFamily( myStyle, "Dialog" );
            StyleConstants.setFontSize( myStyle, 28 );
            attributes.put( "big", myStyle ); 

            // Bold
            myStyle = sc.addStyle( null, null );
            StyleConstants.setBold( myStyle, true );
            attributes.put( "bold", myStyle ); 
                        
    }//End public void createStyles( StyleContext sc ) 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        newQuery = new javax.swing.JButton();
        openQuery = new javax.swing.JButton();
        saveQuery = new javax.swing.JButton();
        executeQuery = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        clearOutput = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        compileLexer = new javax.swing.JButton();
        compileCup = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        queryTabs = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        queryText = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNewSql = new javax.swing.JMenuItem();
        itemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kick Ass Database");
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        newQuery.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        newQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/script_add.png"))); // NOI18N
        newQuery.setText("New Query");
        newQuery.setFocusable(false);
        newQuery.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newQuery.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newQueryActionPerformed(evt);
            }
        });
        jToolBar1.add(newQuery);

        openQuery.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        openQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/folder_explore.png"))); // NOI18N
        openQuery.setText("Open Query");
        openQuery.setFocusable(false);
        openQuery.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openQuery.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openQueryActionPerformed(evt);
            }
        });
        jToolBar1.add(openQuery);

        saveQuery.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/script_save.png"))); // NOI18N
        saveQuery.setText("Save Query");
        saveQuery.setFocusable(false);
        saveQuery.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveQuery.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveQueryActionPerformed(evt);
            }
        });
        jToolBar1.add(saveQuery);

        executeQuery.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        executeQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/script_go.png"))); // NOI18N
        executeQuery.setText("Run Query");
        executeQuery.setEnabled(false);
        executeQuery.setFocusable(false);
        executeQuery.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        executeQuery.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        executeQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeQueryActionPerformed(evt);
            }
        });
        jToolBar1.add(executeQuery);
        jToolBar1.add(jSeparator1);

        clearOutput.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clearOutput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/page_white.png"))); // NOI18N
        clearOutput.setText("Clear Output");
        clearOutput.setFocusable(false);
        clearOutput.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clearOutput.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clearOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearOutputActionPerformed(evt);
            }
        });
        jToolBar1.add(clearOutput);
        jToolBar1.add(jSeparator2);

        compileLexer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compileLexer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/directory_listing.png"))); // NOI18N
        compileLexer.setText("Compile Lexer");
        compileLexer.setFocusable(false);
        compileLexer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compileLexer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compileLexer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileLexerActionPerformed(evt);
            }
        });
        jToolBar1.add(compileLexer);

        compileCup.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compileCup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cup.png"))); // NOI18N
        compileCup.setText("Compile Cup");
        compileCup.setFocusable(false);
        compileCup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compileCup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compileCup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileCupActionPerformed(evt);
            }
        });
        jToolBar1.add(compileCup);

        outputText.setEditable(false);
        outputText.setColumns(20);
        outputText.setRows(5);
        jScrollPane2.setViewportView(outputText);

        jScrollPane1.setViewportView(queryText);

        queryTabs.addTab("query1", jScrollPane1);

        jMenu1.setText("File");

        itemNewSql.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SQL_Icon.png"))); // NOI18N
        itemNewSql.setText("New SQL");
        jMenu1.add(itemNewSql);

        itemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit_Icon.png"))); // NOI18N
        itemExit.setText("Exit");
        jMenu1.add(itemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(queryTabs, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queryTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compileLexerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileLexerActionPerformed
        
        // Call Compile Lexer Method
        String path = "src\\kickassdb\\lexer.flex";
        KickAssDB.generateLexer(path);        
    }//GEN-LAST:event_compileLexerActionPerformed

    private void compileCupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileCupActionPerformed
        
        // Call Compile CUP method
        KickAssDB.generateCup();
    }//GEN-LAST:event_compileCupActionPerformed

    private void executeQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeQueryActionPerformed
        
        /* Store the query as a string */
        String query = queryText.getText();
        
        getOutputText().append("Executing Query... \n");
        this.parserResult = "Parsing Successful";
        
        /* Call the Parser */
        try
        {
            /* Lexer lexer = new Lexer(new java.io.FileInputStream(opened_file.getAbsolutePath()));                        
            while (!lexer.next_token().value.equals("End of File.")){                            
                this.tempLexerText += lexer.yytext() + "\n";                
            } */
            
            parser p = new parser(new Lexer(new java.io.FileInputStream(opened_file.getAbsolutePath())));
            p.parse();
            
            this.outputText.append(this.parserResult);
            
        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_executeQueryActionPerformed

    private void newQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newQueryActionPerformed
        
        /* Enabled components */
        queryText.setText("");
    }//GEN-LAST:event_newQueryActionPerformed

    private void saveQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveQueryActionPerformed
                        
        try
        {
            // Check if an opened file will be saved
            if ( opened_file != null )
            {
                // Save changes to the current file
                FileWriter fw = new FileWriter(opened_file);
                PrintWriter pw = new PrintWriter(fw);

                pw.write(queryText.getText());
                getOutputText().setText("File saved successfully. \n");
                pw.close();
            }
            else
            {
                // Show File Chooser to save new file
                JFileChooser fc = new JFileChooser();
                FileFilter ft = new FileNameExtensionFilter( "Text Files", "txt" );                
                fc.addChoosableFileFilter( ft );
                int retVal = fc.showSaveDialog(null);

                if ( retVal == fc.APPROVE_OPTION )
                {
                    File nuevoArchivo = new File( fc.getSelectedFile().getAbsolutePath());

                    // Create file on selected directory
                    FileWriter fw = new FileWriter(nuevoArchivo);
                    PrintWriter pw = new PrintWriter(fw);

                    pw.write(queryText.getText());
                    getOutputText().setText("File " + nuevoArchivo.getName() + " has been saved successfully. \n");
                    pw.close();

                    opened_file = nuevoArchivo;
                }
            }
        }
        catch ( Exception e )
        {
            getOutputText().setText(e.getMessage());
        }
        
        executeQuery.setEnabled(true);
    }//GEN-LAST:event_saveQueryActionPerformed

    private void openQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openQueryActionPerformed
        
        // Create File Chooser
        JFileChooser fc = new JFileChooser("src\\queries");
        
        int seleccion = fc.showOpenDialog(null);

        if ( seleccion == fc.APPROVE_OPTION )
        {
            // Select the file
            opened_file = fc.getSelectedFile();
            
            // Add it to the text pane
            try
            {
                FileReader fr = new FileReader(opened_file);
                BufferedReader reader = new BufferedReader(fr);
                
                String line;
                String input = "";
                while ( (line = reader.readLine()) != null )
                    input += line + "\n";

                queryText.setText(input);
                fr.close();
                reader.close();
                getOutputText().append("Opened file: " + opened_file.getName() + "\n");
            }
            catch (Exception e )
            {
                getOutputText().setText(e.getMessage());
            }
        }
        
        executeQuery.setEnabled(true);
    }//GEN-LAST:event_openQueryActionPerformed

    private void clearOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearOutputActionPerformed
        
        getOutputText().setText("");
    }//GEN-LAST:event_clearOutputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearOutput;
    private javax.swing.JButton compileCup;
    private javax.swing.JButton compileLexer;
    private javax.swing.JButton executeQuery;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemNewSql;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton newQuery;
    private javax.swing.JButton openQuery;
    private javax.swing.JTextArea outputText;
    private javax.swing.JTabbedPane queryTabs;
    private javax.swing.JTextPane queryText;
    private javax.swing.JButton saveQuery;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the outputText
     */
    public javax.swing.JTextArea getOutputText() {
        return outputText;
    }
}