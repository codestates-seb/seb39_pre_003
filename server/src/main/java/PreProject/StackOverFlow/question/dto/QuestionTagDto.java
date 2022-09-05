package PreProject.StackOverFlow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionTagDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Post{
        private long questionId;
        private long tagId;
    }
}
