package PreProject.StackOverFlow.tag.controller;

import PreProject.StackOverFlow.tag.dto.TagDto;
import PreProject.StackOverFlow.tag.entity.Tag;
import PreProject.StackOverFlow.tag.mapper.TagMapper;
import PreProject.StackOverFlow.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @PostMapping("/add")
    public ResponseEntity createTag(@RequestBody TagDto.Post post){
        Tag tag = tagMapper.tagPostToTag(post);
        tagService.createTag(tag);
        return new ResponseEntity<>(tagMapper.tagToTagResponse(tag), HttpStatus.OK);
    }
}
