/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kickassdb;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.HashMap;

/**
 *
 * @author Jaime Andres
 */
public class AddIndexes extends javax.swing.JFrame {

    /**
     * Creates new form AddIndexes
     */
    public AddIndexes() {
        initComponents();
        
        this.tableIndexes.setModel(new DefaultListModel());
        
        this.loadTables();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        indexAlgorithms = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDomain = new javax.swing.JList();
        addIndex = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableIndexes = new javax.swing.JList();
        removeIndex = new javax.swing.JButton();
        treeRadio = new javax.swing.JRadioButton();
        hashRadio = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setTitle("Indexes");
        setResizable(false);

        tableList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        tableList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                tableListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        jScrollPane2.setViewportView(tableDomain);

        addIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        addIndex.setText("Add Index");
        addIndex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIndexActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(tableIndexes);

        removeIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removeIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        removeIndex.setText("Remove Index");
        removeIndex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeIndexActionPerformed(evt);
            }
        });

        indexAlgorithms.add(treeRadio);
        treeRadio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        treeRadio.setSelected(true);
        treeRadio.setText("Tree Type Indexing");

        indexAlgorithms.add(hashRadio);
        hashRadio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hashRadio.setText("Hashing Type Indexing");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tables");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Attributes");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Indexes");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tree.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/table_select_column.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(removeIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(addIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(treeRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(hashRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addIndex)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(removeIndex))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(treeRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hashRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_tableListValueChanged
        
        Table sTable = (Table)this.tableList.getSelectedValue();
                
        /* Display table attributes in list */
        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel indexesModel = new DefaultListModel();
        
        for ( Attribute attr : sTable.getTable_domain())
        {
            /* Check if attribute is already an index */
            if ( attr.getIndexType() == Attribute.IndexType.NULL ) 
                listModel.addElement(attr);
        }
        
        for ( Object i : sTable.getIndexes() )
            indexesModel.addElement(i);
        
        this.tableDomain.setModel(listModel);
        this.tableIndexes.setModel(indexesModel);
        
        if ( sTable.getIndexes().size() >= 2 )
            addIndex.setEnabled(false);
        else
            addIndex.setEnabled(true);
        
    }//GEN-LAST:event_tableListValueChanged

    private void addIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIndexActionPerformed
        
        int selectedIndex = this.tableDomain.getSelectedIndex();
        
        if ( selectedIndex == -1 )
            return;
        
        /* Add attributes selected to the indexes list */
        DefaultListModel indexesModel = (DefaultListModel) this.tableIndexes.getModel();
        DefaultListModel attributesModel = (DefaultListModel) tableDomain.getModel();
                        
        Attribute at = (Attribute) this.tableDomain.getSelectedValue(); // Selected Attribute to Index  
        indexesModel.addElement(at); // Add it to indexes list
        
        /* Remove the added attribute from the attributes list */
        attributesModel.remove(selectedIndex);
        
        tableIndexes.setModel(indexesModel);
        
        /* Check if there are more than 2 indexes selected */
        if ( tableIndexes.getModel().getSize() >= 2 )
            addIndex.setEnabled(false);
        
        
        /* Set the Indexing type for this attribute */
        if ( treeRadio.isSelected() )
        {
            /* B-TREE TYPE INDEXING */
            
            at.setIndexType(Attribute.IndexType.TREE_TYPE_INDEXING);
            
            /* Add all the values of the table to the B Tree */
            BPlusTree tree = at.getBPlusTree();
            Table selectedTable = (Table) tableList.getSelectedValue(); // Get the selected Table object
            int position = selectedTable.getFieldPosition(at.getAttribute_name()); // Attribute's position in domain
            
            /* Check the Attribute type */
            if ( at.getType() == Attribute.Type.INTEGER )
            {
                /* Iterate through all the tuples */
                for ( Tuple tuple : selectedTable.getTable_tuples() )                
                    tree.add( Integer.parseInt(tuple.getValue(position).getValue().toString()), tuple);
                    
            }
            else if ( at.getType() == Attribute.Type.VARCHAR )
            {
                /* Iterate through all the tuples */
                for ( Tuple tuple : selectedTable.getTable_tuples() )                
                    tree.add( tuple.getValue(position).getValue().toString().length(), tuple);                
            }
            
            at.setBPlusTree(tree);
            
            System.out.println(tree.toString());
            
        }
        else if ( hashRadio.isSelected() )
        {
            /* HASH TYPE INDEXING */
            
            at.setIndexType(Attribute.IndexType.HASH_TYPE_INDEXING);
            
            HashMap<Integer, Tuple> hm = new HashMap<>();            
            Table selectedTable = (Table) tableList.getSelectedValue();
                        
            int position = selectedTable.getFieldPosition(at.getAttribute_name());
            
            /* Check the Attribute type */
            if ( at.getType() == Attribute.Type.INTEGER )
            {
                /* Iterate through all the tuples */
                for ( Tuple tuple : selectedTable.getTable_tuples() )                
                    hm.put( Integer.parseInt(tuple.getValue(position).getValue().toString()), tuple);                
            }
            else if ( at.getType() == Attribute.Type.VARCHAR )
            {
                /* Iterate through all the tuples */
                for ( Tuple tuple : selectedTable.getTable_tuples() )                
                    hm.put( tuple.getValue(position).getValue().toString().length(), tuple);                
            }
            
            at.setHashTable(hm);
        }
        
        /* Add the index to the table */
        Table indexed_table = (Table) this.tableList.getSelectedValue();
        indexed_table.addIndex(at);
        
        JOptionPane.showMessageDialog(this, "Index created on attribute " + at.getAttribute_name() + " on table " + indexed_table.getTable_name(), "Indexed Added Succesfully", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_addIndexActionPerformed

    private void removeIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeIndexActionPerformed
        
        int selectedIndex = this.tableIndexes.getSelectedIndex();        
        
        if ( selectedIndex == -1 )
            return;
        
        /* Add attributes selected to the attributes list */
        DefaultListModel indexesModel = (DefaultListModel) tableIndexes.getModel();
        DefaultListModel attributesModel = (DefaultListModel) tableDomain.getModel();
                        
        Attribute at = (Attribute) this.tableIndexes.getSelectedValue();         
        attributesModel.addElement(at);
        
        /* Remove the selected attribute from the indexes list */                
        indexesModel.remove(selectedIndex);
                
        tableDomain.setModel(attributesModel);
        
        /* Check if there are more than 2 indexes selected */
        addIndex.setEnabled(true);
        
        /* Remove Data Structure associated with this attribute */                
        at.setBPlusTree(new BPlusTree());
        at.setHashTable(new HashMap());
        at.setIndexType(Attribute.IndexType.NULL);
                        
        /* Remove the index from the Table's index list */
        Table indexed_table = (Table) this.tableList.getSelectedValue();
        indexed_table.getIndexes().remove(at);
        
        JOptionPane.showMessageDialog(this, "Index " + at.getAttribute_name() + " has been removed from table " + indexed_table.getTable_name(), "Indexed Removed Succesfully", JOptionPane.WARNING_MESSAGE);
                
    }//GEN-LAST:event_removeIndexActionPerformed

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
            java.util.logging.Logger.getLogger(AddIndexes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddIndexes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddIndexes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddIndexes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddIndexes().setVisible(true);
            }
        });
    }
    
    private void loadTables()
    {
        /* Loop the schema */
        Schema schema = MainWindow.getDefaultSchema();
        DefaultListModel listModel = new DefaultListModel();
        
        for ( Table t : schema.getSchema() )
        {
            listModel.addElement(t);
        }
        
        tableList.setModel(listModel);
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addIndex;
    private javax.swing.JRadioButton hashRadio;
    private javax.swing.ButtonGroup indexAlgorithms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton removeIndex;
    private javax.swing.JList tableDomain;
    private javax.swing.JList tableIndexes;
    private javax.swing.JList tableList;
    private javax.swing.JRadioButton treeRadio;
    // End of variables declaration//GEN-END:variables
}
