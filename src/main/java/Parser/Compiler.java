package Parser;

import Lexer.*;

import java.util.ArrayList;

public class Compiler {
    private ArrayList astTree = new ArrayList();
    private ArrayList<Token> tokenList = new ArrayList<Token>();
    private int current = 0;
    private CodeGen codeGen = new CodeGen();
    private String code;

    public Compiler(String code) {
        this.code = code;
    }

    public void compile() {
        validate();
        generateCode();
    }

    private void validate() {
        Lexer lexer = new Lexer(code);

        while (true) {
            Token token = lexer.NextToken();
            if (token.getSyntaxKind() == SyntaxKind.badToken) {
                System.out.println(codeGen.failCompilation(token));
                System.exit(1);

            }
            if (!token.getSyntaxKind().equals(SyntaxKind.whitespace)) {
                tokenList.add(token);
            }
            if (token.getSyntaxKind() == SyntaxKind.endOfFile) {
                break;
            }
//            System.out.println(token.getSyntaxKind() + " " + token.getText());
        }


        if ((!tokenList.get(0).getText().equals("int")) && (!tokenList.get(0).getText().equals("char"))) {
            // to do
            System.out.println(codeGen.failCompilation(tokenList.get(0)));
            System.exit(1);

        } else if (tokenList.get(1).getSyntaxKind() != SyntaxKind.identifierMain) {
            // to do
            System.out.println(codeGen.failCompilation(tokenList.get(1)));
            System.exit(1);
        } else if (!(tokenList.get(2).getSyntaxKind() == SyntaxKind.openParethesOperator)) {
            // to do
            System.out.println(codeGen.failCompilation(tokenList.get(2)));
            System.exit(1);
        } else if (!(tokenList.get(3).getSyntaxKind() == SyntaxKind.closeParethesOperator)) {
            // to do
            System.out.println(codeGen.failCompilation(tokenList.get(3)));
            System.exit(1);
        } else if (!(tokenList.get(4).getSyntaxKind() == SyntaxKind.openFigureParethesOperator)) {
            // to do
            System.out.println(codeGen.failCompilation(tokenList.get(4)));
            System.exit(1);
        } else if (!(tokenList.get(tokenList.size() - 2).getSyntaxKind() == SyntaxKind.closeFigureParethesOperator)) {
            // to do
            System.out.println(codeGen.failCompilation(tokenList.get(tokenList.size() - 2)));
            System.exit(1);
        }


    }

    private void generateCode() {
        StringBuffer buffer = new StringBuffer();
        boolean flag = false;
        int positionReturn = 0;
        for (int i = 0; i < tokenList.size() - 1; i++) {
            if (tokenList.get(i).getSyntaxKind() == SyntaxKind.identifierReturn) {
                positionReturn = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            if (tokenList.get(positionReturn + 1).getSyntaxKind() == SyntaxKind.number
                    ||tokenList.get(positionReturn + 1).getSyntaxKind() == SyntaxKind.octalNumber

                    && tokenList.get(positionReturn + 2).getSyntaxKind() == SyntaxKind.semicolonOperator
                    && (tokenList.get(0).getSyntaxKind() == SyntaxKind.identifierInt)) {
                System.out.println(codeGen.generate(tokenList.get(positionReturn + 1)));
            } else if (tokenList.get(positionReturn + 1).getSyntaxKind() == SyntaxKind.charEl
                    && tokenList.get(positionReturn + 2).getSyntaxKind() == SyntaxKind.semicolonOperator
                    && (tokenList.get(0).getSyntaxKind() == SyntaxKind.identifierChar)) {
                System.out.println(codeGen.generate(tokenList.get(positionReturn + 1)));
            } else {
                System.out.println("error");
            }
        } else {
            System.out.println("error");
        }
    }

}
