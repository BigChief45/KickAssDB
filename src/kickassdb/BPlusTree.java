/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kickassdb;

import java.util.ArrayList;

/**
 *
 * @author Otto
 */
public class BPlusTree {
        class Node {
                public int mNumKeys = 0;
                public int[] mKeys = new int[2 * T - 1];
                public Object[] mObjects = new Object[2 * T - 1];
                public BPlusTree.Node[] mChildNodes = new BPlusTree.Node[2 * T];
                public boolean mIsLeafNode;
                public BPlusTree.Node mNextNode;
        }
        
        private BPlusTree.Node mRootNode;
        private static final int T = 4;
        
        public BPlusTree() {
                mRootNode = new BPlusTree.Node();
                mRootNode.mIsLeafNode = true;           
        }
        
        public void add(int key, Object object) {
                BPlusTree.Node rootNode = mRootNode;
                if (rootNode.mNumKeys == (2 * T - 1)) {
                        BPlusTree.Node newRootNode = new BPlusTree.Node();
                        mRootNode = newRootNode;
                        newRootNode.mIsLeafNode = false;
                        mRootNode.mChildNodes[0] = rootNode;
                        splitChildNode(newRootNode, 0, rootNode); // Split rootNode and move its median (middle) key up into newRootNode.
                        insertIntoNonFullNode(newRootNode, key, object); // Insert the key into the B-Tree with root newRootNode.
                } else {
                        insertIntoNonFullNode(rootNode, key, object); // Insert the key into the B-Tree with root rootNode.
                }
        }
        
        // Split the node, node, of a B-Tree into two nodes that contain T-1 (and T) elements and move node's median key up to the parentNode.
        // This method will only be called if node is full; node is the i-th child of parentNode.
        // All internal keys (elements) will have duplicates within the leaf nodes.
        void splitChildNode(BPlusTree.Node parentNode, int i, BPlusTree.Node node) {
                BPlusTree.Node newNode = new BPlusTree.Node();
                newNode.mIsLeafNode = node.mIsLeafNode;
                newNode.mNumKeys = T;
                for (int j = 0; j < T; j++) { // Copy the last T elements of node into newNode. Keep the median key as duplicate in the first key of newNode.
                        newNode.mKeys[j] = node.mKeys[j + T - 1];
                        newNode.mObjects[j] = node.mObjects[j + T - 1];
                }
                if (!newNode.mIsLeafNode) {
                        for (int j = 0; j < T + 1; j++) { // Copy the last T + 1 pointers of node into newNode.
                                newNode.mChildNodes[j] = node.mChildNodes[j + T - 1];
                        }
                        for (int j = T; j <= node.mNumKeys; j++) {
                                node.mChildNodes[j] = null;
                        }
                } else {
                        // Manage the linked list that is used e.g. for doing fast range queries.
                        newNode.mNextNode = node.mNextNode;
                        node.mNextNode = newNode;
                }
                for (int j = T - 1; j < node.mNumKeys; j++) {
                        node.mKeys[j] = 0;
                        node.mObjects[j] = null;
                }
                node.mNumKeys = T - 1;
                
                // Insert a (child) pointer to node newNode into the parentNode, moving other keys and pointers as necessary.
                for (int j = parentNode.mNumKeys; j >= i + 1; j--) {
                        parentNode.mChildNodes[j + 1] = parentNode.mChildNodes[j];
                }
                parentNode.mChildNodes[i + 1] = newNode;        
                for (int j = parentNode.mNumKeys - 1; j >= i; j--) {
                        parentNode.mKeys[j + 1] = parentNode.mKeys[j];
                        parentNode.mObjects[j + 1] = parentNode.mObjects[j];
                }
                parentNode.mKeys[i] = newNode.mKeys[0];
                parentNode.mObjects[i] = newNode.mObjects[0];
                parentNode.mNumKeys++;
        }
        
        // Insert an element into a B-Tree. (The element will ultimately be inserted into a leaf node). 
        void insertIntoNonFullNode(BPlusTree.Node node, int key, Object object) {
                int i = node.mNumKeys - 1;
                if (node.mIsLeafNode) {
                        // Since node is not a full node insert the new element into its proper place within node.
                        while (i >= 0 && key < node.mKeys[i]) {
                                node.mKeys[i + 1] = node.mKeys[i];
                                node.mObjects[i + 1] = node.mObjects[i];
                                i--;
                        }
                        i++;
                        node.mKeys[i] = key;
                        node.mObjects[i] = object;
                        node.mNumKeys++;                        
                } else {
                        // Move back from the last key of node until we find the child pointer to the node
                        // that is the root node of the subtree where the new element should be placed.
                        while (i >= 0 && key < node.mKeys[i]) {
                                i--;
                        }
                        i++;
                        if (node.mChildNodes[i].mNumKeys == (2 * T - 1)) {
                                splitChildNode(node, i, node.mChildNodes[i]);
                                if (key > node.mKeys[i]) {
                                        i++;
                                }
                        }
                        insertIntoNonFullNode(node.mChildNodes[i], key, object);
                }
        }       
        
        // Recursive search method.
        public Object search(BPlusTree.Node node, int key) {              
                int i = 0;
                while (i < node.mNumKeys && key > node.mKeys[i]) {
                        i++;
                }
                if (i < node.mNumKeys && key == node.mKeys[i]) {
                        return node.mObjects[i];
                }
                if (node.mIsLeafNode) {
                        return null;
                } else {
                        return search(node.mChildNodes[i], key);
                }       
        }
                        
        public Object search(int key) {
                return search(mRootNode, key);
        }
        
        // Recursive search method.
        public BPlusTree.Node searchNode(BPlusTree.Node node, int key) {              
                int i = 0;
                while (i < node.mNumKeys && key > node.mKeys[i]) {
                        i++;
                }
                if (i < node.mNumKeys && key == node.mKeys[i]) {
                        return node;
                }
                if (node.mIsLeafNode) {
                        return null;
                } else {
                        return searchNode(node.mChildNodes[i], key);
                }       
        }        
        
        public BPlusTree.Node searchNode(int key) {
                return searchNode(mRootNode, key);
        }                
        
        // Iterative search method.
        public Object search2(BPlusTree.Node node, int key) {
                while (node != null) {
                        int i = 0;
                        while (i < node.mNumKeys && key > node.mKeys[i]) {
                                i++;
                        }
                        if (i < node.mNumKeys && key == node.mKeys[i]) {
                                return node.mObjects[i];
                        }
                        if (node.mIsLeafNode) {
                                return null;
                        } else {
                                node = node.mChildNodes[i];
                        }
                }
                return null;
        }
        
        public Object search2(int key) {
                return search2(mRootNode, key);
        }
        
        public ArrayList getLess(int key){
        
            ArrayList result = new ArrayList();
            
            BPlusTree.Node node = mRootNode;
            while (!node.mIsLeafNode) {
                
                node = node.mChildNodes[0];
                
            }//End while (!node.mIsLeafNode)
            
            while (node != null) {

                //We iterate in all the keys of the node
                for (int i = 0; i < node.mNumKeys; i++){
                    
                    int temp = node.mKeys[i];
                    
                    if ( temp < key  ) {
                    
                        result.add(node.mObjects[i]);
                        
                    } else {
                    
                        break;
                    
                    }//End else
                        
                }//End for (int i = 0; i < node.mNumKeys; i++)
                
                node = node.mNextNode;
                
            }//End while (node != null)
            
            return result;
        
        }//End public ArrayList getLess()        

        public ArrayList getEquals(int key){
        
            ArrayList result = new ArrayList();              
            
            BPlusTree.Node node = getLeafNodeForKey(key);
            while (node != null) {
                    for (int j = 0; j < node.mNumKeys; j++) {

                        if (key == node.mKeys[j]){
                        
                            result.add(node.mObjects[j]);
                            
                        }else if (node.mKeys[j] > key){
                        
                            return result;
                            
                        }
                                                    
                    }
                    node = node.mNextNode;
            }
            
            return result;
            
        }//End public ArrayList getGreater()
                
        public ArrayList getGreater(int key){
        
            ArrayList result = new ArrayList();              
            
            BPlusTree.Node node = getLeafNodeForKey(key);
            while (node != null) {
                    for (int j = 0; j < node.mNumKeys; j++) {

                            if (node.mKeys[j] > key) {
                                result.add(node.mObjects[j]);
                            }
                            
                    }
                    node = node.mNextNode;
            }
            
            return result;
            
        }//End public ArrayList getGreater()
        
        public ArrayList getDifferent(int key){
        
            ArrayList result = new ArrayList();
            
            BPlusTree.Node node = mRootNode;
            while (!node.mIsLeafNode) {
                
                node = node.mChildNodes[0];
                
            }//End while (!node.mIsLeafNode)
            
            while (node != null) {

                //We iterate in all the keys of the node
                for (int i = 0; i < node.mNumKeys; i++){
                    
                    int temp = node.mKeys[i];
                    
                    if ( temp != key  ) {
                    
                        result.add(node.mObjects[i]);
                        
                    }
                        
                }//End for (int i = 0; i < node.mNumKeys; i++)
                
                node = node.mNextNode;
                
            }//End while (node != null)
            
            return result;        
        
        }//End public ArrayList getLess()        
                
        // Inorder walk over the tree.
        @Override
        public String toString() {
                String string = "";
                BPlusTree.Node node = mRootNode;          
                while (!node.mIsLeafNode) {                     
                        node = node.mChildNodes[0];
                }               
                while (node != null) {
                        for (int i = 0; i < node.mNumKeys; i++) {
                                string += node.mObjects[i] + ", ";
                        }
                        node = node.mNextNode;
                }
                return string;
        }
        
        // Inorder walk over parts of the tree.
        public String toString(int fromKey, int toKey) {
                String string = "";
                BPlusTree.Node node = getLeafNodeForKey(fromKey);
                while (node != null) {
                        for (int j = 0; j < node.mNumKeys; j++) {
                                string += node.mObjects[j] + ", ";
                                if (node.mKeys[j] == toKey) {
                                        return string;
                                }
                        }
                        node = node.mNextNode;
                }
                return string;
        }
        
        BPlusTree.Node getLeafNodeForKey(int key) {
                BPlusTree.Node node = mRootNode;
                while (node != null) {
                        int i = 0;
                        while (i < node.mNumKeys && key > node.mKeys[i]) {
                                i++;
                        }
                        if (i < node.mNumKeys && key == node.mKeys[i]) {
                                node = node.mChildNodes[i + 1];
                                while (!node.mIsLeafNode) {                     
                                        node = node.mChildNodes[0];
                                }
                                return node;
                        }
                        if (node.mIsLeafNode) {
                                return null;
                        } else {
                                node = node.mChildNodes[i];
                        }
                }
                return null;
        }

}
