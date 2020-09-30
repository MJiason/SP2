package Parser;

import Lexer.Token;

public class NodeToken {
    private Token value;
    private NodeToken children;

    public NodeToken(Token value) {
        this.value = value;
    }

    public NodeToken getChildren() {
        return children;
    }

    public void setChildren(NodeToken children) {
        this.children = children;
    }
}
