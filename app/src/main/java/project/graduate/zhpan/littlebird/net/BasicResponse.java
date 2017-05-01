package project.graduate.zhpan.littlebird.net;

/**
 *
 */
public class BasicResponse<T> {

    private String status;
    private String message;
    private String errMsg;

    private boolean error;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    private T results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrMsg() {
        return errMsg;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
