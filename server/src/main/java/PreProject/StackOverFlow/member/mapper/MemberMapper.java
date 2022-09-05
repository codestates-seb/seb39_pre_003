package PreProject.StackOverFlow.member.mapper;

import PreProject.StackOverFlow.member.dto.MemberDto;
import PreProject.StackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post postMember);
    Member memberPatchToMember(MemberDto.Patch patchMember);
    MemberDto.Response memberToMemberResponse(Member member);
    List<MemberDto.Response> membersToMemberResponses(List<Member> members);
}