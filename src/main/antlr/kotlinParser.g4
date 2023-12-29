parser grammar kotlinParser;

options{ tokenVocab=kotlinLexer; }

kotlinFile: line+ (NEWLINE | EOF);

line: statement;

/*
 var name = "Simone";
 val age : Int = 16;
 var height : Double = 1.75;
 const val text = "Hello World";
 */

 /* TASK 1 : VARIABILI
 --------------------------------------------*/

 statement : variableDeclaration | assign;

 variableDeclaration
    : VAR ID (COLONS type)? ASSIGN expression       #varDeclaration
    | VAL ID (COLONS type)? ASSIGN expression       #valDeclaration
    | CONST ID (COLONS type)? ASSIGN expression     #constDeclaration
    ;

 assign : ID ASSIGN expression;

 type
    : STRING       #stringType
    | INT          #intType
    | DOUBLE       #doubleType
    | BOOLEAN      #booleanType
    | ID           #referenceType
    ;

 expression
    : StringLiteral     #stringExpression
    | INT_LIT           #integerExpression
    | DOUBLE_LIT        #doubleExpression
    | BOOL_LIT          #booleanExpression
    ;


