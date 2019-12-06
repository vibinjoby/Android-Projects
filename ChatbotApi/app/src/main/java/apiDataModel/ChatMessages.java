package apiDataModel;

public class ChatMessages {
    private int side;
    private String message;

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessages{" +
                "side=" + side +
                ", message='" + message + '\'' +
                '}';
    }
}
