
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Mon Mar 24 14:45:44 CST 2014
//----------------------------------------------------

package kickassdb;

import java.io.*;
import java_cup.runtime.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Mon Mar 24 14:45:44 CST 2014
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\033\000\002\002\004\000\002\002\005\000\002\002" +
    "\005\000\002\002\003\000\002\002\003\000\002\002\002" +
    "\000\002\003\010\000\002\014\002\000\002\006\007\000" +
    "\002\007\003\000\002\007\006\000\002\010\004\000\002" +
    "\010\002\000\002\011\004\000\002\011\002\000\002\004" +
    "\012\000\002\012\005\000\002\012\002\000\002\015\002" +
    "\000\002\013\006\000\002\013\003\000\002\016\002\000" +
    "\002\005\006\000\002\017\002\000\002\005\006\000\002" +
    "\005\003\000\002\005\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\010\002\ufffc\004\005\010\010\001\002\000" +
    "\006\002\ufffe\020\065\001\002\000\004\005\042\001\002" +
    "\000\006\002\ufffd\020\040\001\002\000\004\002\037\001" +
    "\002\000\004\011\011\001\002\000\004\022\012\001\002" +
    "\000\006\012\ufff0\016\014\001\002\000\004\012\023\001" +
    "\002\000\004\022\015\001\002\000\006\017\uffed\021\uffef" +
    "\001\002\000\004\017\017\001\002\000\004\012\ufff1\001" +
    "\002\000\004\021\021\001\002\000\004\022\015\001\002" +
    "\000\004\017\uffee\001\002\000\004\016\024\001\002\000" +
    "\006\023\025\024\026\001\002\000\006\017\uffe8\021\uffec" +
    "\001\002\000\006\017\uffe7\021\uffea\001\002\000\004\017" +
    "\030\001\002\000\006\002\ufff2\020\ufff2\001\002\000\004" +
    "\021\032\001\002\000\006\023\025\024\026\001\002\000" +
    "\004\017\uffe9\001\002\000\004\021\035\001\002\000\006" +
    "\023\025\024\026\001\002\000\004\017\uffeb\001\002\000" +
    "\004\002\001\001\002\000\010\002\ufffc\004\005\010\010" +
    "\001\002\000\004\002\uffff\001\002\000\004\022\043\001" +
    "\002\000\004\016\044\001\002\000\004\022\045\001\002" +
    "\000\006\014\052\015\051\001\002\000\004\017\047\001" +
    "\002\000\006\002\ufffb\020\ufffb\001\002\000\010\006\056" +
    "\017\ufff3\021\ufff3\001\002\000\004\016\053\001\002\000" +
    "\010\006\ufff8\017\ufff8\021\ufff8\001\002\000\004\023\054" +
    "\001\002\000\004\017\055\001\002\000\010\006\ufff7\017" +
    "\ufff7\021\ufff7\001\002\000\004\007\064\001\002\000\006" +
    "\017\ufffa\021\ufffa\001\002\000\006\017\ufff5\021\061\001" +
    "\002\000\004\022\045\001\002\000\004\017\ufff9\001\002" +
    "\000\004\017\ufff6\001\002\000\006\017\ufff4\021\ufff4\001" +
    "\002\000\010\002\ufffc\004\005\010\010\001\002\000\004" +
    "\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\010\002\006\003\003\004\005\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\012" +
    "\012\001\001\000\002\001\001\000\004\013\015\001\001" +
    "\000\004\015\017\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\013\021\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\005\026\001\001\000" +
    "\004\016\033\001\001\000\004\017\030\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\005" +
    "\032\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\005\035\001\001\000\002\001\001\000\002\001\001\000" +
    "\010\002\040\003\003\004\005\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\006\045\001" +
    "\001\000\004\007\047\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\011\056\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\014\057\001\001\000" +
    "\004\010\061\001\001\000\004\006\062\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\010\002" +
    "\065\003\003\004\005\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


             
     public static void main(String args[]) throws Exception
        {
          //new parser(new Lexer(new FileInputStream(args[0]))).parse();
          new parser(new Lexer(System.in)).parse();               
     }

     public void syntax_error(Symbol s)
        {
          //report_error("Error de sintaxis. Linea: " + (s.right + 1) + " Columna: " + s.left + ". Texto: \"" + s.value + "\"", null);
                KickAssDB.mainwindow.parserResult = "Syntax Error. Line: " + (s.right + 1) + " Column: " + s.left + ". Text: \"" + s.value + "\"" + "\n";
     }

     public void unrecovered_syntax_error(Symbol s)
        {               
                KickAssDB.mainwindow.parserResult = "Fatal Syntax Error. Line: " + (s.right + 1) + " Column: " + s.left + ". Text: \"" + s.value + "\"" + "\n";               
     }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {


    private static ArrayList<Attribute> domain_temp = new ArrayList<Attribute>();
    private static ArrayList<Attribute> primaryKey_temp = new ArrayList<Attribute>();
    private static Tuple temp_tuple = new Tuple();
    private static Attribute temp_pk = null;
    private static int pk_count = 0;
    private static ArrayList<String> attName = new ArrayList<String>();
    private static ArrayList<String> valType = new ArrayList<String>();
   

  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // record_values ::= STR 
            {
              Object RESULT =null;
		int valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int valright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                                        Value v = new Value(val);
                                        temp_tuple.addValue(v);
                                        valType.add("VARCHAR");
                                  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("record_values",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // record_values ::= NUM 
            {
              Object RESULT =null;
		int valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int valright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                                        Value v = new Value(val);
                                        temp_tuple.addValue(v);
                                        valType.add("INTEGER");
                                  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("record_values",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // record_values ::= STR NT$3 COMA record_values 
            {
              Object RESULT =null;
              // propagate RESULT from NT$3
                RESULT = (Object) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Object val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("record_values",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // NT$3 ::= 
            {
              Object RESULT =null;
		int valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int valright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;

                                        Value v = new Value(val);
                                        temp_tuple.addValue(v);
                                        valType.add("VARCHAR");
                                  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$3",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // record_values ::= NUM NT$2 COMA record_values 
            {
              Object RESULT =null;
              // propagate RESULT from NT$2
                RESULT = (Object) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Object val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("record_values",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // NT$2 ::= 
            {
              Object RESULT =null;
		int valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int valright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
                                       
                                        Value v = new Value(val);
                                        temp_tuple.addValue(v);
                                        valType.add("INTEGER");
                                  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$2",12, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // attribute_names ::= ID 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 attName.add(id); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("attribute_names",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // attribute_names ::= ID NT$1 COMA attribute_names 
            {
              Object RESULT =null;
              // propagate RESULT from NT$1
                RESULT = (Object) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("attribute_names",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // NT$1 ::= 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
 attName.add(id); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$1",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // attributes ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("attributes",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // attributes ::= PARLEFT attribute_names PARRIGHT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("attributes",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // insert_record ::= INSERT INTO ID attributes VALUES PARLEFT record_values PARRIGHT 
            {
              Object RESULT =null;
		int table_nameleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).left;
		int table_nameright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).right;
		String table_name = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-5)).value;
		
                        /* Find the table to insert */                       
                        Schema schema = KickAssDB.mainwindow.getDefault_schema();
                        boolean finished_loop = true;
                       
                        /* Check if any tables exist */
                        if ( schema.getTable_count() > 0 )
                        {
                            /* Look on all tables and search for the table */
                            for ( Table t : schema.getSchema() )
                            {
                                if ( t.getTable_name().toLowerCase().equals(table_name.toString().toLowerCase()) )
                                {
                                    /* Table found, validate tuple before inserting */
                                   
                                    /* Obtain a new tuple with correct attribute value ordering */
                                    Tuple new_tuple;
                                    new_tuple = Tuple.setTuple_order(attName, t.getTable_domain(), temp_tuple);

                                    boolean val;

                                    val = Validations.validateInsertingTuple(attName, valType, t) && Validations.validateColumnSize(temp_tuple); 
                                    if ( val && t.getPrimary_key() != null )
                                        val = Validations.validatePrimaryKey(new_tuple, t);
                                   
                                    attName.clear();
                                    valType.clear();

                                    /* Insert tuple into the table */
                                    if(val) 
                                    {
                                        t.addTuple(new_tuple);
                                        t.printTuples();
                                    }

                                    /* Reset the temp tuple */
                                    temp_tuple = new Tuple();

                                    /* Exit from the cycle */
                                    finished_loop = false;
                                    break;
                                }                                                                                                                                   
                            }

                            if ( finished_loop == true )
                            {
                                /* Table Not Found */
                                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Table '" + table_name.toString() + "' does not exist. Record was not inserted.", "Error", JOptionPane.ERROR_MESSAGE);                                         
                            }
                         }
                         else
                         {
                            /* Table Not Found */
                            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Table '" + table_name.toString() + "' does not exist. Record was not inserted.", "Error", JOptionPane.ERROR_MESSAGE);                                         
                         }
                  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("insert_record",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-7)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // primary_key ::= 
            {
              Object RESULT =null;
		            
                                        /* Primary Key Production false */                          
                                        RESULT = false;
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("primary_key",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // primary_key ::= PRIMARY KEY 
            {
              Object RESULT =null;
		      
                                        /* Primary Key Production true */                                       
                                        RESULT = true;
                                        pk_count++;
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("primary_key",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // coma ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("coma",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // coma ::= COMA table_attribute 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("coma",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // attribute_type ::= VARCHAR PARLEFT NUM PARRIGHT 
            {
              Object RESULT =null;
		
                                    /* Attribute's type is Varchar */
                                    RESULT = Attribute.Type.VARCHAR;
                               
              CUP$parser$result = parser.getSymbolFactory().newSymbol("attribute_type",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // attribute_type ::= INT 
            {
              Object RESULT =null;
		
                                    /* Attribute type is Integer */
                                    RESULT = Attribute.Type.INTEGER;
                               
              CUP$parser$result = parser.getSymbolFactory().newSymbol("attribute_type",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // table_attribute ::= ID attribute_type primary_key NT$0 coma 
            {
              Object RESULT =null;
              // propagate RESULT from NT$0
                RESULT = (Object) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int typeleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int typeright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Object type = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int pkleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int pkright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object pk = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("table_attribute",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // NT$0 ::= 
            {
              Object RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int typeleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int typeright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object type = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int pkleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int pkright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object pk = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;

                               /* Get current Attribute's name */                              
                               Attribute current_attribute = new Attribute(name.toString(), (Attribute.Type)type);
                               domain_temp.add(current_attribute);

                               /* Check if this attribute is possible primary key */
                               if ( (boolean) pk == true )
                               {
                                   temp_pk = current_attribute; 
                               }
                        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$0",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // create_table ::= CREATE TABLE ID PARLEFT table_attribute PARRIGHT 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		
                        /* Get the current default schema */
                        Schema schema = KickAssDB.mainwindow.getDefault_schema();

                        /* Get current table count for default schema */
                        int table_count = schema.getTable_count();
                                        
                        /* Check if table name is already taken */
                        if ( table_count > 0 )
                        {
                            for ( Table t : schema.getSchema() )
                            {
                                if ( t.getTable_name().equals(id.toString()) )
                                {
                                    JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Table '" + id.toString() + "' Already Exists.", "Error", JOptionPane.ERROR_MESSAGE);
                                    //System.out.print("error here2");
                                    return null; // ???
                                }
                            }
                        }

                        /* If table name does not exist, proceed */
                        ArrayList<Attribute> temp = new ArrayList<Attribute>();
                       
                        /* Instance a new table */
                        Table table = new Table(table_count, id.toString(), domain_temp);

                        /* If there is primary key, set it */
                        if ( pk_count == 1 || pk_count == 0)
                        {
                            table.setPrimary_key(temp_pk);

                            /* Add table to default schema */
                            schema.addTable(table);

                            table.printDomain();                       

                        }
                        else if ( pk_count > 1 )
                        {
                            /* If there is already more than one PK Statement, display error */
                            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Table cannot contain more than one Primary Key.", "Error", JOptionPane.ERROR_MESSAGE);                                                                   
                        }

                            /* Reset the temporal domain and primary key */
                            domain_temp = new ArrayList<Attribute>();
                            temp_pk = new Attribute();
                            pk_count = 0;

                 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("create_table",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // operation ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // operation ::= insert_record 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // operation ::= create_table 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // operation ::= insert_record SEMICOLON operation 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // operation ::= create_table SEMICOLON operation 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= operation EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

