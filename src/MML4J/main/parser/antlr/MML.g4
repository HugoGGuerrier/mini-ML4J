grammar MML;

@header {
package MML4J.main.parser.antlr;
}

// ----- Lexing rules

// Syntaxic keywords
FN : 'fn' ;
FN_REC : 'fnrec' ;
NIL : 'nil' ;
IF_ZERO : 'ifz' ;
IF_EMPTY : 'ifem' ;
LET : 'let' ;
IN : 'in' ;

// Build in keyword
HEAD : 'head' ;
TAIL : 'tail' ;
CONS : 'cons' ;

// Operators an build in function
BIN_OP : '+'|'-' ;
BUILD_IN : HEAD|TAIL|CONS ;

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
    | FN_REC '(' param=IDENT ')' '{' body=expr '}' # RecAbstraction
    ;

op_args :
    arg=expr # OpSoleArg
    | arg=expr ',' tail=op_args # OpMultiArgs
    ;

//params :
//    IDENT # SingleParam
//    | head=IDENT ',' tail=params # MultipleParam
//    ;