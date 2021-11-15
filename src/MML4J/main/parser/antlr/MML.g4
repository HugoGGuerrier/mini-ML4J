grammar MML;

@header {
package MML4J.main.parser.antlr;
}

// ----- Lexing rules

// Keywords
FN : 'fn' ;
REC : 'rec' ;
IF_ZERO : 'ifz' ;
IF_EMPTY : 'ifem' ;
ELSE : 'else' ;
LET : 'let' ;
IN : 'in' ;
NIL : 'nil' ;
UNIT : '()' ;

// Operators an build in function
UN_OP : '!' | '@' ;
BIN_OP : '+' | '-' | ':=' ;
BUILD_IN : 'cons'|'head'|'tail' ;

// Regex symbols
INTEGER : '-'?[0-9]+;
IDENT : [a-zA-Z_]+ ;

// Ignored
IGNORED__ : [ \n\r\t] -> skip ;


// ----- Parsing rules

program : body=expr EOF # Prog;

expr :
    IDENT # Variable
    | INTEGER # Integer
    | NIL # Nil
    | UNIT # Unit
    | '(' inside=expr ')' # Priorised
    | '[' inside=exprs ']' # ListSugar
    | op=UN_OP arg=expr # UnOp
    | left=expr op=BIN_OP right=expr # BinOp
    | name=BUILD_IN '(' arguments=exprs ')' # BuildIn
    | func=expr '(' arg=expr ')' # Application
    | IF_ZERO '(' cond=expr ')' '{' cons=expr '}' ELSE '{' altern=expr '}' # IfZero
    | IF_EMPTY '(' cond=expr ')' '{' cons=expr '}' ELSE '{' altern=expr '}' # IfEmpty
    | FN '(' parameters=params ')' '{' body=expr '}' # Abstraction
    | REC name=IDENT '(' param=IDENT ')' '{' body=expr '}' # RecAbstraction
    | LET name=IDENT '=' value=expr IN body=expr # LetIn
    ;

exprs :
    # VoidExprs
    | head=expr # SingleExprs
    | head=expr ',' tail=exprs # MultiExprs
    ;

params :
    head=IDENT # SingleParams
    | head=IDENT ',' tail=params # MultipleParams
    ;