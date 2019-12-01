package apiDataModel;

public class Status {
    private String code;
    private String errorType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code='" + code + '\'' +
                ", errorType='" + errorType + '\'' +
                '}';
    }
}
