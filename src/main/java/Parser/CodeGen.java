package Parser;

import Lexer.SyntaxKind;
import Lexer.Token;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CodeGen {
    public String generate(Token token) {
        int tokenValue = 0;
        if (token.getSyntaxKind() == SyntaxKind.octalNumber){
            int newText = Integer.parseInt(token.getText(), 8);
            token = new Token(SyntaxKind.number, String.valueOf(newText), token.getPosition());
        }
        if (token.getSyntaxKind() == SyntaxKind.charEl) {
            tokenValue = token.getText().charAt(0);
        } else {
            tokenValue = Integer.parseInt(token.getText());
        }
        String code = "main:\n" +
                "mov eax,"+ tokenValue + "\nret\nend main.";

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("input path for: ");
            String path = scanner.nextLine();
            FileWriter writer = new FileWriter(path + "output.txt");
            System.out.println("File output create");
            writer.write(code);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return code;
    }

    public String failCompilation(Token token) {
        return "compilation fail " + token.getText() + " on line " + token.getPosition();
    }
}
