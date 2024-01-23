parser grammar KotlinParser;

options{ tokenVocab=KotlinLexer; }

kotlinFile: lines=line+;

line: statement (NL* | EOF);

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
    | classDefinition                               #ClassDefinitionStatement
    | expression                                    #ExpressionDefinitionStatement
    | objectProperty ASSIGN expression              #ObjectPropertyAssignStatement
    | RETURN expression                             #ReturnStatement
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
    | functionCall                                                                      #FunctionCallExpression
    | objectProperty                                                                    #ObjectPropertyExpression
    | objectMethodCall                                                                  #ObjectMethodCallExpression
    ;

parameter
    : (VAL | VAR )? ID COLONS type
    ;

block: GRAPH_OPEN NL* (statement NL+)* NL* GRAPH_CLOSE;

functionDefinition
    : FUN name=ID PAREN_OPEN
        (parameter COMMA)* parameter?
    PAREN_CLOSE
    (COLONS returnType=type)?
    block;

classDefinition:
    CLASS name=ID (PAREN_OPEN (parameter COMMA)* parameter? PAREN_CLOSE)? GRAPH_OPEN NL* (classStatement NL+)* GRAPH_CLOSE;

classStatement
   : initBlock                  #InitStatement
   | constructorBlock           #SecondaryConstructorStatement
   | functionDefinition         #MethodStatement
   ;

initBlock: INIT GRAPH_OPEN NL* (statement NL+)*  GRAPH_CLOSE ;

constructorBlock:
    CONSTRUCTOR PAREN_OPEN NL*
        (parameter COMMA)* parameter?
    PAREN_CLOSE COLONS thisConstructor block?;

thisConstructor: THIS PAREN_OPEN (expression COMMA)* expression? PAREN_CLOSE;

functionCall: name=ID PAREN_OPEN (expression COMMA)* expression? PAREN_CLOSE;
objectProperty: objectName=(ID | THIS) DOT propertyName=ID;
objectMethodCall: objectName=(ID | THIS) DOT functionCall;
