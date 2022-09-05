package PreProject.StackOverFlow.tag.mapper;

import PreProject.StackOverFlow.tag.dto.TagDto;
import PreProject.StackOverFlow.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    Tag tagPostToTag(TagDto.Post post);
    TagDto.Response tagToTagResponse(Tag tag);
}
