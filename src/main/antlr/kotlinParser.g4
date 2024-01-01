parser grammar kotlinParser;

options{ tokenVocab=kotlinLexer; }

kotlinFile: lines=line+;

line: statement (NEWLINE | EOF);

/*
 var name = "Simone"
 val age : Int = 16
 var height : Double = 1.75
 const val text = "Hello World"
 */

 /* TASK 1 : VARIABILI
 --------------------------------------------*/

 statement
    : variableDeclaration #VariableDeclarationStatement
    | assign #AssignStatement
    ;

 variableDeclaration
    : VAR ID (COLONS type)? ASSIGN expression       #VarDeclarationStatement
    | VAL ID (COLONS type)? ASSIGN expression       #ValDeclarationStatement
    | CONST ID (COLONS type)? ASSIGN expression     #ConstDeclarationStatement
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


