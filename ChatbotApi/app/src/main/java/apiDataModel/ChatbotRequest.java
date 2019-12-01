package apiDataModel;

public class ChatbotRequest {

    private String lang;
    private String query;
    private String sessionId;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "ChatbotRequest{" +
                "lang='" + lang + '\'' +
                ", query='" + query + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
