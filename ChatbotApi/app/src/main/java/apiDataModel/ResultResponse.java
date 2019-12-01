package apiDataModel;

public class ResultResponse {
    private String source;
    private String resolvedQuery;
    private String action;
    private FulfillmentResponse fulfillment;
    private Status status;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public void setResolvedQuery(String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public FulfillmentResponse getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(FulfillmentResponse fulfillment) {
        this.fulfillment = fulfillment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "source='" + source + '\'' +
                ", resolvedQuery='" + resolvedQuery + '\'' +
                ", action='" + action + '\'' +
                ", fulfillment=" + fulfillment +
                ", status=" + status +
                '}';
    }
}
