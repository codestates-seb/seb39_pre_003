package PreProject.StackOverFlow.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    private ExceptionCode exceptionCode;

    private String exception_Msg;


    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BusinessLogicException(String exception_Msg) {
        this.exception_Msg = exception_Msg;
    }
}