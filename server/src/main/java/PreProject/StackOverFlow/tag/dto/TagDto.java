package PreProject.StackOverFlow.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class TagDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String name;
        private String contents;
    }

    public static class Patch{

    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        private String name;
        private String contents;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}
