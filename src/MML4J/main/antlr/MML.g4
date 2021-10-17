grammar MML;

@header {
package MML4J.main.antlr;
}

// ----- Lexing rules

// Keywords
FN : 'fn' ;

// Regex symbols
IDENT : [a-zA-Z]+ ;

// Ignored
IGNORED__ : [ \n\r\t] -> skip ;


// ----- Parsing rules

program : body=expr EOF # Prog;

expr :
    IDENT # Variable
    | '(' inside=expr ')' # Priorised
    | func=expr '(' arg=expr ')' # Application
    | FN '(' param=IDENT ')' '{' body=expr '}' # Abstraction
    ;

//params :
//    IDENT # SingleParam
//    | head=IDENT ',' tail=params # MultipleParam
//    ;