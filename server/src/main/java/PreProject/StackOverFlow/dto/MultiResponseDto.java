package PreProject.StackOverFlow.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


@ApiModel(description = "복합 정보 전송 모델")
@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
