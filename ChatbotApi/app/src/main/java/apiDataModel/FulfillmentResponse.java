package apiDataModel;

public class FulfillmentResponse {
    private String speech;

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    @Override
    public String toString() {
        return "FulfillmentResponse{" +
                "speech='" + speech + '\'' +
                '}';
    }
}
