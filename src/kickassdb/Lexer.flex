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
	return new Symbol(sym.EOF,new String("Fin del archivo"));
%eofval}

// Character Set
letra = [a-zA-z]
digito = [0-9]
espacio = \t | \f | " " | \r | \n
especiales = "!" | "?"

// Delimitadores 
op_declaracion = ":"
op_asignacion = ":="
op_rel = "=" | "/=" | "<" | "<=" | ">" | ">="
op_suma = "+" | "-"
op_mult = "*" | "/"
parentesis_izquierdo = "("
parentesis_derecho = ")"
dospuntos = ".."
puntoycoma = ";"
coma = ","
caja = "<>"

// Identificadores
id = {letra}+("_"({letra}|{numero})+)*({letra}|{numero})*

// Numeros
punto = "."
signo_negativo = "-"
numero = ({signo_negativo}|""){digito}+(({punto}{digito}+)|"")

// Comentarios
comentario = "--"({letra}|{digito}|" ")+

// String
str = "\"" ({letra} | {digito} | {espacio} | {especiales} )* "\""

%%

<YYINITIAL>
{	
	
	"PROCEDURE" { return new Symbol(sym.PROCEDURE, yycolumn, yyline, yytext()); }
	"FUNCTION" { return new Symbol(sym.FUNCTION, yycolumn, yyline, yytext()); }
	"RETURN" { return new Symbol(sym.RETURN, yycolumn, yyline, yytext()); }
	"IS" { return new Symbol(sym.IS, yycolumn, yyline, yytext()); }
	"BEGIN" { return new Symbol(sym.BEGIN, yycolumn, yyline, yytext()); }
	"END" { return new Symbol(sym.END, yycolumn, yyline, yytext()); }
	"IF" { return new Symbol(sym.IF, yycolumn, yyline, yytext()); }
	"THEN" { return new Symbol(sym.THEN, yycolumn, yyline, yytext()); }
	"ELSE" { return new Symbol(sym.ELSE, yycolumn, yyline, yytext()); }
	"ELSIF" { return new Symbol(sym.ELSIF, yycolumn, yyline, yytext()); }
	"FOR" { return new Symbol(sym.FOR, yycolumn, yyline, yytext()); }
	"IN" { return new Symbol(sym.IN, yycolumn, yyline, yytext()); }
	"OUT" {return new Symbol(sym.OUT, yycolumn, yyline, yytext()); }
	"WHILE" { return new Symbol(sym.WHILE, yycolumn, yyline, yytext()); }
	"LOOP" { return new Symbol(sym.LOOP, yycolumn, yyline, yytext()); }
	"RANGE" { return new Symbol(sym.RANGE, yycolumn, yyline, yytext()); }	
	"AND" { return new Symbol(sym.AND, yycolumn, yyline, yytext()); }
	"OR" { return new Symbol(sym.OR, yycolumn, yyline, yytext()); }
	"XOR" { return new Symbol(sym.XOR, yycolumn, yyline, yytext()); }
	"TRUE" { return new Symbol(sym.TRUE, yycolumn, yyline, yytext()); }
	"FALSE" { return new Symbol(sym.FALSE, yycolumn, yyline, yytext()); }
	"EXIT" { return new Symbol(sym.EXIT, yycolumn, yyline, yytext()); }
	"WHEN" { return new Symbol(sym.WHEN, yycolumn, yyline, yytext()); }
	"ARRAY" { return new Symbol(sym.ARRAY, yycolumn, yyline, yytext()); }
	"OF" { return new Symbol(sym.OF, yycolumn, yyline, yytext()); }
	
	"Put" { return new Symbol(sym.PUT, yycolumn, yyline, yytext()); }
	"Get" { return new Symbol(sym.GET, yycolumn, yyline, yytext()); }
	
	"Integer" { return new Symbol(sym.INT, yycolumn, yyline, yytext()); }
	"Boolean" { return new Symbol(sym.BOOLEAN, yycolumn, yyline, yytext()); }
	"Float" { return new Symbol(sym.FLOAT, yycolumn, yyline, yytext()); }
	
	{op_declaracion} { return new Symbol(sym.OPDEC, yycolumn, yyline, yytext()); }
	{op_asignacion} { return new Symbol(sym.OPASG, yycolumn, yyline, yytext()); }
	{op_rel} { return new Symbol(sym.OPREL, yycolumn, yyline, yytext()); }
	{op_suma} { return new Symbol(sym.OPSUM, yycolumn, yyline, yytext()); }
	{op_mult} { return new Symbol(sym.OPMULT, yycolumn, yyline, yytext()); }
	{parentesis_izquierdo} { return new Symbol(sym.PARIZQ, yycolumn, yyline, yytext()); }
	{parentesis_derecho} { return new Symbol(sym.PARDER, yycolumn, yyline, yytext()); }
	{dospuntos} { return new Symbol(sym.DOSPUNTOS, yycolumn, yyline, yytext()); }
	{puntoycoma} { return new Symbol(sym.PYC, yycolumn, yyline, yytext()); }
	{coma} { return new Symbol(sym.COMA, yycolumn, yyline, yytext()); }
	{caja} { return new Symbol(sym.CAJA, yycolumn, yyline, yytext()); }
	{str} { return new Symbol(sym.STR, yycolumn, yyline, yytext()); }
	
	
	{id} { return new Symbol(sym.ID, yycolumn, yyline, yytext()); }
	
	{numero} { return new Symbol(sym.NUM, yycolumn, yyline, yytext()); }
	
	
	{espacio} {}
	{comentario} {}
	
	
	. { System.out.println("Caracter ilegal: " + yytext()); }

}