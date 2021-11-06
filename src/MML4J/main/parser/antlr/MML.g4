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
ELSE : 'else' ;
LET : 'let' ;
IN : 'in' ;

// Operators an build in function
BIN_OP : '+'|'-' ;
BUILD_IN : 'cons'|'head'|'tail' ;

// Regex symbols
INTEGER : '-'?[0-9]+;
IDENT : [a-zA-Z]+ ;

// Ignored
IGNORED__ : [ \n\r\t] -> skip ;


// ----- Parsing rules

program : body=expr EOF # Prog;

expr :
    IDENT # Variable
    | INTEGER # Integer
    | NIL # Nil
    | '(' inside=expr ')' # Priorised
    | left=expr op=BIN_OP right=expr # BinOp
    | name=BUILD_IN '(' arguments=args ')' # BuildIn
    | func=expr '(' arg=expr ')' # Application
    | IF_ZERO '(' cond=expr ')' '{' cons=expr '}' ELSE '{' altern=expr '}' # IfZero
    | IF_EMPTY '(' cond=expr ')' '{' cons=expr '}' ELSE '{' altern=expr '}' # IfEmpty
    | FN '(' param=IDENT ')' '{' body=expr '}' # Abstraction
    | FN_REC '(' param=IDENT ')' '{' body=expr '}' # RecAbstraction
    | LET name=IDENT '=' value=expr IN body=expr # LetIn
    ;

args :
    arg=expr # SoleArgs
    | arg=expr ',' tail=args # MultiArgs
    ;

//params :
//    IDENT # SingleParam
//    | head=IDENT ',' tail=params # MultipleParam
//    ;