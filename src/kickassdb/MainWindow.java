package kickassdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java_cup.runtime.Symbol;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledEditorKit;

public class MainWindow extends javax.swing.JFrame 
{        
	private Hashtable attributes;
        private File opened_file;
        public String parserResult = "Parsing Successful";
        private static Schema default_schema;
                       
    /**
     * Creates new form MainWindow
     */
    public MainWindow() 
    {
        initComponents();
        
        /* Instantiate Application's Default Schema */
        default_schema = new Schema("Untitled Schema");
        
        /* Set Text Line Numbers */
        TextLineNumber tln = new TextLineNumber(this.queryText);
        tln.setUpdateFont(true);
        tln.setBackground(Color.DARK_GRAY);
        tln.setForeground(Color.LIGHT_GRAY);
        tln.setCurrentLineForeground(Color.WHITE);
        this.queryScrollPane.setRowHeaderView(tln);
        
        /* Load Syntax Highlighter */
        loadSyntaxHighlighter();
                
    }//End MainWindow()

    private void loadSyntaxHighlighter()
    {
        EditorKit editorKit = new StyledEditorKit()
        {
            public Document createDefaultDocument()
            {
                MutableAttributeSet attr = MultiSyntaxDocument.DEFAULT_KEYWORD;
                HashMap<String, MutableAttributeSet> keywords = new HashMap<String, MutableAttributeSet>();
                
                /* Get Keywords and add them to the Highlighter */
                Keywords kws = new Keywords();
                
                for ( String s : kws.getKeywords() )
                {
                    keywords.put( s,   attr );
                }
                                
                SimpleAttributeSet operatorTest = new SimpleAttributeSet();
                MultiSyntaxDocument.setAttributeColor( operatorTest, new Color( 255, 0, 0 ) );
                MultiSyntaxDocument.setAttributeFont( operatorTest, new Font( "Courier New", Font.BOLD, 11 ) );
                keywords.put( "dound",         operatorTest );
                
                MultiSyntaxDocument doc = new MultiSyntaxDocument( keywords );
                doc.setTabs( 4 );
                
                return doc;
            }
        };
        
        queryText.setEditorKitForContentType("text/java", editorKit);
        queryText.setContentType("text/java");
                
        JButton button = new JButton("Load Java File");
        button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String fn = "MultiSyntaxDocument.java";
                    FileInputStream fis = new FileInputStream( fn );
                    queryText.read(fis, null);
                    queryText.requestFocus();
                }
                catch(Exception e2) 
                {
                    System.out.println( e2.getMessage() + "\n" + e2.getStackTrace() );
                }
            }
        });	
    }
    
    protected static void showQueryOutput(Table table)
    {    
        ResultsPanel.removeAll();
                                
        /* Assemble the data from the recieved table */
        String[] columnNames = new String[table.getTable_domain().size()];
        Object[][] data = new Object[table.getTable_tuples().size()][columnNames.length]; 

        /* Add the domain attribute names */
        int i = 0;
        for (Attribute domain : table.getTable_domain()) 
        {
            columnNames[i] = domain.getAttribute_name();            
            i++;                
        }
        
        /* Add the tuples */
        i = 0;
        for (Tuple tuple : table.getTable_tuples()) 
        {
            int j = 0;
            for (Value value : tuple.getTuple_values()) 
            {                    
                data[i][j] = value.getValue().toString();                
                j++;
            }            
            i++;
        }            
        
        JTable tempTable = new JTable(data, columnNames);            
        JScrollPane tempscrollPane = new JScrollPane(tempTable);
        tempTable.setFillsViewportHeight(true);
                               
        ResultsPanel.add(tempscrollPane);
        ResultsPanel.setLayout(new BorderLayout());
        ResultsPanel.add(tempTable.getTableHeader(), BorderLayout.PAGE_START);
        ResultsPanel.add(tempTable, BorderLayout.CENTER);
        ResultsPanel.revalidate();
        ResultsPanel.repaint();
                        
    }
      
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
        openSchema = new javax.swing.JButton();
        saveSchema = new javax.swing.JButton();
        viewSchema = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        clearOutput = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        compileLexer = new javax.swing.JButton();
        compileCup = new javax.swing.JButton();
        queryTabs = new javax.swing.JTabbedPane();
        queryScrollPane = new javax.swing.JScrollPane();
        queryText = new javax.swing.JTextPane();
        ReviewTab = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        ResultsPanel = new javax.swing.JPanel();
        currentSchema_label = new javax.swing.JLabel();
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

        openSchema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        openSchema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/database_edit.png"))); // NOI18N
        openSchema.setText("Open Schema");
        openSchema.setFocusable(false);
        openSchema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openSchema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(openSchema);

        saveSchema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveSchema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/database_save.png"))); // NOI18N
        saveSchema.setText("Save Schema");
        saveSchema.setFocusable(false);
        saveSchema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveSchema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(saveSchema);

        viewSchema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        viewSchema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/database_table.png"))); // NOI18N
        viewSchema.setText("View Schema");
        viewSchema.setFocusable(false);
        viewSchema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewSchema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSchemaActionPerformed(evt);
            }
        });
        jToolBar1.add(viewSchema);
        jToolBar1.add(jSeparator3);

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

        queryText.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        queryText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                queryTextKeyTyped(evt);
            }
        });
        queryScrollPane.setViewportView(queryText);

        queryTabs.addTab("new_query", queryScrollPane);

        ReviewTab.setToolTipText("");
        ReviewTab.setName(""); // NOI18N

        outputText.setEditable(false);
        outputText.setColumns(20);
        outputText.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        outputText.setRows(5);
        jScrollPane2.setViewportView(outputText);

        ReviewTab.addTab("Output", jScrollPane2);

        ResultsPanel.setLayout(new javax.swing.BoxLayout(ResultsPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane5.setViewportView(ResultsPanel);

        ReviewTab.addTab("Result", jScrollPane5);

        currentSchema_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        currentSchema_label.setText("Current Schema: Untitled Schema");

        jMenu1.setText("File");

        itemNewSql.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SQL_Icon.png"))); // NOI18N
        itemNewSql.setText("New SQL");
        jMenu1.add(itemNewSql);

        itemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit_Icon.png"))); // NOI18N
        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        jMenu1.add(itemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tools");
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
                    .addComponent(queryTabs, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ReviewTab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(currentSchema_label)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queryTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReviewTab, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(currentSchema_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        outputText.append("Compiling CUP file... \n");
        KickAssDB.generateCup();
        outputText.append("CUP file compiled successfully. \n");
    }//GEN-LAST:event_compileCupActionPerformed

    private void executeQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeQueryActionPerformed
        
        /* Store the query as a string */
        String query = queryText.getText();
        
        getOutputText().append("Executing Query... \n");
        this.parserResult = "Parsing Successful \n";
        
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
            System.err.println(e.getMessage());
        }
        
        //this.printDatabase();         //changed here
        
    }//GEN-LAST:event_executeQueryActionPerformed

    private void newQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newQueryActionPerformed
        
        /* Enabled components */
        queryText.setText("");
        opened_file = null;
        executeQuery.setEnabled(false);
        queryTabs.setTitleAt(0, "new_query");
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
                getOutputText().setText("File " + opened_file.getName() + " saved successfully. \n");
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
                                
                queryTabs.setTitleAt(0, opened_file.getName());
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

    private void queryTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_queryTextKeyTyped
        /* Disable Run Query Button */
        executeQuery.setEnabled(false);
    }//GEN-LAST:event_queryTextKeyTyped

    private void viewSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSchemaActionPerformed
        
        /* Open View Schema window */
        ViewSchema vs = new ViewSchema();
        
        vs.setLocationRelativeTo(null);
        vs.setVisible(true);
    }//GEN-LAST:event_viewSchemaActionPerformed

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemExitActionPerformed

    
    protected static Schema getDefaultSchema()
    {
        return default_schema;
    }
    
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
    private static javax.swing.JPanel ResultsPanel;
    private javax.swing.JTabbedPane ReviewTab;
    private javax.swing.JButton clearOutput;
    private javax.swing.JButton compileCup;
    private javax.swing.JButton compileLexer;
    private static javax.swing.JLabel currentSchema_label;
    private javax.swing.JButton executeQuery;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemNewSql;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton newQuery;
    private javax.swing.JButton openQuery;
    private javax.swing.JButton openSchema;
    protected static javax.swing.JTextArea outputText;
    private javax.swing.JScrollPane queryScrollPane;
    private javax.swing.JTabbedPane queryTabs;
    private javax.swing.JTextPane queryText;
    private javax.swing.JButton saveQuery;
    private javax.swing.JButton saveSchema;
    private javax.swing.JButton viewSchema;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the outputText
     */
    public javax.swing.JTextArea getOutputText() {
        return outputText;
    }

    /**
     * @return the default_schema
     */
    public Schema getDefault_schema() {
        return default_schema;
    }

    public static void setDefault_schema(Schema sc) {
        default_schema = sc;
        
        currentSchema_label.setText("Current Schema: " + default_schema.getName());
    }
    
    public static void saveSchema(Schema schema)
    {
        /* Create a folder with the schema name */
        File folder = new File("src/schemas/" + schema.getName());
        folder.mkdir();
        
        /* Create the Schema File inside the folder */
        
        /* Assign Default Schema */
        setDefault_schema(schema);
    }
}
