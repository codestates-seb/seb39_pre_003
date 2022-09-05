package PreProject.StackOverFlow.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "Member Exists"),
    MEMBER_NOT_FOUND(404, "Member not found");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String messgae){
        this.status = code;
        this.message = messgae;
    }
}