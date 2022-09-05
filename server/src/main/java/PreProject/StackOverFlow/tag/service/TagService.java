package PreProject.StackOverFlow.tag.service;

import PreProject.StackOverFlow.exception.BusinessLogicException;
import PreProject.StackOverFlow.tag.dto.TagDto;
import PreProject.StackOverFlow.tag.entity.Tag;
import PreProject.StackOverFlow.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public Tag createTag(Tag tag){
        verifyExists(tag.getName());
        return tagRepository.save(tag);
    }

    private void verifyExists(String name){
        Tag check = tagRepository.findByName(name);
        if(check != null){
            throw new BusinessLogicException("Tag Already Exist");
        }
    }
}
