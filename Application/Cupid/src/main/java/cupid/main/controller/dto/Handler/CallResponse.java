package cupid.main.controller.dto.Handler;

import lombok.Getter;

public class CallResponse<T> {
    @Getter
    private final T value;
    @Getter
    private final Integer errorCode;
    @Getter
    private final String errorMessage;

    public CallResponse(T value){
        if(value == null) {
            throw new IllegalArgumentException("value cannot be null when there is no error");
        }
        this.value = value;
        this.errorCode = null;
        this.errorMessage = null;
    }

    public CallResponse(Integer errorCode, String errorMessage){

        if (errorCode == null || errorMessage == null){
            throw new IllegalArgumentException("errorCode and errorMessage cannot be null");
        }

        this.value = null;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean Failed(){
        return errorCode != null && errorMessage != null;
    }
    public boolean hasValue() { return value != null; }


}
