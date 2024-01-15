parser grammar kotlinParser;

options{ tokenVocab=kotlinLexer; }

kotlinFile: lines=line+;

line: statement EOF?;

/*
 var name = "Simone"
 val age : Int = 16
 var height : Double = 1.75
 const val text = "Hello World"
 */

 /* TASK 1 : VARIABILI
 --------------------------------------------*/

 statement
    : VAR ID (COLONS type)? ASSIGN expression       #VarDeclarationStatement
    | VAL ID (COLONS type)? ASSIGN expression       #ValDeclarationStatement
    | CONST ID (COLONS type)? ASSIGN expression     #ConstDeclarationStatement
    | ID ASSIGN expression                          #AssignStatement
    | functionDefinition                            #FunctionDefinitionStatement
    ;

 type
    : STRING       #StringType
    | INT          #IntType
    | DOUBLE       #DoubleType
    | BOOLEAN      #BooleanType
    | UNIT         #UnitType
    | ID           #CustomType
    ;

expression
    : value=BOOL_LIT                                                                    #BoolLiteralExpression
    | value=INT_LIT                                                                     #IntLiteralExpression
    | value=DOUBLE_LIT                                                                  #DoubleLiteralExpression
    | value=STRING_LIT                                                                  #StringLiteralExpression
    | left=expression     operand=PLUS                  right=expression                #BinaryMathExpression
    | left=expression     operand=MINUS                 right=expression                #BinaryMathExpression
    | left=expression     operand=TIMES                 right=expression                #BinaryMathExpression
    | left=expression     operand=DIVISION              right=expression                #BinaryMathExpression
    | left=expression     operand=MODULE*               right=expression                #BinaryMathExpression
    | left=expression     operand=AND                   right=expression                #BinaryLogicExpression
    | left=expression     operand=OR                    right=expression                #BinaryLogicExpression
    | left=expression     operand=GREATER_THAN          right=expression                #BinaryLogicExpression
    | left=expression     operand=LOWER_THAN            right=expression                #BinaryLogicExpression
    | left=expression     operand=GREATER_EQUAL_THAN    right=expression                #BinaryLogicExpression
    | left=expression     operand=LOWER_EQUAL_THAN      right=expression                #BinaryLogicExpression
    | left=expression     operand=EQUAL                 right=expression                #BinaryLogicExpression
    | left=expression     operand=NOT_EQUAL             right=expression                #BinaryLogicExpression
    |                     operand=MINUS                 value=expression                #UnaryMathExpression
    |                     operand=PLUS                  value=expression                #UnaryMathExpression
    | NOT  value=expression                                                             #UnaryLogicNegationExpression
    | PAREN_OPEN  value=expression  PAREN_CLOSE                                         #ParenthesisExpression
    | value=ID                                                                          #VarReferenceExpression
    | name=ID PAREN_OPEN (expression COMMA)* expression? PAREN_CLOSE                    #FunctionCallExpression
    ;

parameter
    : ID COLONS type
    ;

block: GRAPH_OPEN statements=statement* GRAPH_CLOSE;

functionDefinition
    : FUN name=ID PAREN_OPEN
        (parameter COMMA)* parameter?
    PAREN_CLOSE
    (COLONS returnType=type)?
    block;

