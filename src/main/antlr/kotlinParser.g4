parser grammar kotlinParser;

options{ tokenVocab=kotlinLexer; }

kotlinFile: line+ (NEWLINE | EOF);

line: VAR;