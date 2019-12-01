package apiDataModel;

public class ChatbotResponse {
    private String id;
    private String lang;
    private String sessionId;
    private String timestamp;
    private ResultResponse result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ResultResponse getResult() {
        return result;
    }

    public void setResult(ResultResponse result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ChatbotResponse{" +
                "id='" + id + '\'' +
                ", lang='" + lang + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", result=" + result +
                '}';
    }
}
