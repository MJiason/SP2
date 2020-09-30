package Lexer;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String text;
    private int position = 0;

    public Lexer(String text) {
        this.text = text;
    }

    private char current() {
        if (position >= text.length()) {
            return '\0';
        }
        return text.charAt(position);
    }

    private void Next() {
        position++;
    }

    public Token NextToken() {

        if (position >= text.length()) {
            return new Token(SyntaxKind.endOfFile, "\0", position);
        }

        if (Character.isDigit(current())) {
            int start = position;
            while (Character.isDigit(current())) {
                Next();
            }
            int length = position - start;
            String text = this.text.substring(start, position);
            Pattern pattern = Pattern.compile("^0[1-7][0-7]*$");
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()){
                return new Token(SyntaxKind.octalNumber, text, start);
            }
            return new Token(SyntaxKind.number, text, start);

        }
        if (Character.isLetter(current())) {
            int start = position;
            while (Character.isLetter(current())) {
                Next();
            }
            String text = this.text.substring(start, position);
            if (text.length() == 1 ){
                return new Token(SyntaxKind.charEl, text, position);
            }
            if (text.equals("main")) {
                return new Token(SyntaxKind.identifierMain, text, start, true);
            } else if (text.equals("return")) {
                return new Token(SyntaxKind.identifierReturn, text, start, true);
            } else if (text.equals("int")) {
                return new Token(SyntaxKind.identifierInt, text, start, true);
            }else if (text.equals("char")) {
                return new Token(SyntaxKind.identifierChar, text, start, true);
            }
            return new Token(SyntaxKind.badToken, text, start);

        }

        if (Character.isWhitespace(current())) {
            int start = position;
            while (Character.isWhitespace(current())) {
                Next();
            }
            int length = position - start;
            String text = this.text.substring(start, position);
            return new Token(SyntaxKind.whitespace, text, start);

        }
        if (current() == '+') {
            return new Token(SyntaxKind.plusOperator, "+", position++);
        } else if (current() == '-') {
            return new Token(SyntaxKind.minusOperator, "-", position++);
        } else if (current() == '*') {
            return new Token(SyntaxKind.starOperator, "*", position++);
        } else if (current() == '/') {
            return new Token(SyntaxKind.slashOperator, "/", position++);
        } else if (current() == '(') {
            return new Token(SyntaxKind.openParethesOperator, "(", position++);
        } else if (current() == ')') {
            return new Token(SyntaxKind.closeParethesOperator, ")", position++);
        } else if (current() == '{') {
            return new Token(SyntaxKind.openFigureParethesOperator, "{", position++);
        } else if (current() == '}') {
            return new Token(SyntaxKind.closeFigureParethesOperator, "}", position++);
        } else if (current() == ';') {
            return new Token(SyntaxKind.semicolonOperator, ";", position++);
        }


        return new Token(SyntaxKind.badToken, text.substring(position, position + 1), position++);


    }


}
