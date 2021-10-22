grammar MML;

@header {
package MML4J.main.parser.antlr;
}

// ----- Lexing rules

// Keywords
FN : 'fn' ;
HEAD : 'head' ;
TAIL : 'tail' ;
CONS : 'cons' ;
NIL : 'nil' ;
IF_ZERO : 'ifz' ;
IF_EMPTY : 'ifem' ;
LET : 'let' ;

// Regex symbols
INTEGER : '-'?[0-9] ;
IDENT : [a-zA-Z]+ ;

// Ignored
IGNORED__ : [ \n\r\t] -> skip ;


// ----- Parsing rules

program : body=expr EOF # Prog;

expr :
    IDENT # Variable
    | INTEGER # Integer
    | '(' inside=expr ')' # Priorised
    | func=expr '(' arg=expr ')' # Application
    | FN '(' param=IDENT ')' '{' body=expr '}' # Abstraction
    ;

//params :
//    IDENT # SingleParam
//    | head=IDENT ',' tail=params # MultipleParam
//    ;