package model.exceptions;

@SuppressWarnings("serial")
public class VertexNotFoundException extends Exception {
    public VertexNotFoundException() {
        super("Vertex not found.");
    }
}
