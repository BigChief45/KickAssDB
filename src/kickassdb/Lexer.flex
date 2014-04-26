package kickassdb;
import java_cup.runtime.Symbol;

%%
%cup
%full
%char
%line
%column
%unicode
%class Lexer
%int
%standalone
%ignorecase
%eofval{
	return new Symbol(sym.EOF, new String("End of File."));
%eofval}

// Character Set
letter = [a-zA-z]
digit = [0-9]
space = \t | \f | " " | \r | \n
specials = "!" | "?" | "." | "-"
point = "."

// Delimiters 
op_rel = "=" | "<>" | "<" | ">"
//op_sum = "+" | "-"
//op_mult = "*" | "/"
parenthesis_left = "("
parenthesis_right = ")"
semicolon = ";"
coma = ","

// Identifiers
id = {letter}+("_"({letter}|{number})+)*({letter}|{number})*

// Numbers
point = "."
negative_sign = "-"
number = ({negative_sign}|""){digit}+(({point}{digit}+)|"")

// Comments
comment = "--"( {letter} | {digit} | {str} | {parenthesis_left} | {parenthesis_right} | {semicolon} | {coma} | {point} | (" ") )+

// Strings
str = ("\'") ({letter} | {digit} | {space} | {specials} )* ("\'")


%%

<YYINITIAL>
{	
	"CREATE" { return new Symbol(sym.CREATE, yycolumn, yyline, yytext()); }
        "SCHEMA" { return new Symbol(sym.SCHEMA, yycolumn, yyline, yytext()); }
	"TABLE" { return new Symbol(sym.TABLE, yycolumn, yyline, yytext()); }
        "PRIMARY" { return new Symbol(sym.PRIMARY, yycolumn, yyline, yytext()); }
        "KEY" { return new Symbol(sym.KEY, yycolumn, yyline, yytext()); }
        "INSERT" { return new Symbol(sym.INSERT, yycolumn, yyline, yytext()); }
        "INTO" { return new Symbol(sym.INTO, yycolumn, yyline, yytext()); } 
        "VALUES" { return new Symbol(sym.VALUES, yycolumn, yyline, yytext()); }
        "SELECT" { return new Symbol(sym.SELECT, yycolumn, yyline, yytext()); }
        "FROM" { return new Symbol(sym.FROM, yycolumn, yyline, yytext()); }
        "*" { return new Symbol(sym.ASTERISK, yycolumn, yyline, yytext()); }
        "WHERE" { return new Symbol(sym.WHERE, yycolumn, yyline, yytext()); }
        "AND" { return new Symbol(sym.AND, yycolumn, yyline, yytext()); }
        "OR" { return new Symbol(sym.OR, yycolumn, yyline, yytext()); }
        "COUNT" { return new Symbol(sym.COUNT, yycolumn, yyline, yytext()); }
        "SUM" { return new Symbol(sym.SUM, yycolumn, yyline, yytext()); }
        "AS" { return new Symbol(sym.AS, yycolumn, yyline, yytext()); }


        "int" { return new Symbol(sym.INT, yycolumn, yyline, yytext()); }
        "varchar" { return new Symbol(sym.VARCHAR, yycolumn, yyline, yytext()); }
	
	{parenthesis_left} { return new Symbol(sym.PARLEFT, yycolumn, yyline, yytext()); }
	{parenthesis_right} { return new Symbol(sym.PARRIGHT, yycolumn, yyline, yytext()); }	
	{semicolon} { return new Symbol(sym.SEMICOLON, yycolumn, yyline, yytext()); }
	{coma} { return new Symbol(sym.COMA, yycolumn, yyline, yytext()); }	
        {op_rel} { return new Symbol(sym.OPREL, yycolumn, yyline, yytext()); }
        {point} { return new Symbol(sym.POINT, yycolumn, yyline, yytext()); }

        {str} { return new Symbol(sym.STR, yycolumn, yyline, yytext().replaceAll("'", "")); }
		
	{id} { return new Symbol(sym.ID, yycolumn, yyline, yytext()); }	
	{number} { return new Symbol(sym.NUM, yycolumn, yyline, yytext()); }
	
	
	{space} {}
	{comment} {}
		
	. { System.out.println("Illegal Character: " + yytext()); }

}