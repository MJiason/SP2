package Lexer;

public class Token {
    private SyntaxKind syntaxKind;
    private String text;
    private int position;
    private boolean identifier = false;

    public Token(SyntaxKind syntaxKind, String text, int position) {
        this.syntaxKind = syntaxKind;
        this.text = text;
        this.position = position;
    }

    public Token(SyntaxKind syntaxKind, String text, int position, boolean identifier) {
        this.syntaxKind = syntaxKind;
        this.text = text;
        this.position = position;
        this.identifier = identifier;
    }

    public SyntaxKind getSyntaxKind() {
        return syntaxKind;
    }

    public String getText() {
        return text;
    }

    public int getPosition() {
        return position;
    }

    public boolean isIdentifier() {
        return identifier;
    }
}
