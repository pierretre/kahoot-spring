package com.mapper;

import com.domain.*;
import com.dto.*;
import com.dto.get.UserGetDTO;
import com.dto.post.UserPostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    /**
     * GET
     */
    SessionDTO sessionToSessionDTO(Session session);

    UserGetDTO userToUserDTO(User user);

    KahootDTO kahootToKahootDTO(Kahoot kahoot);

    OrganizerDTO organizerToOrganizerDTO(Organizer organizer);

    QuestionDTO questionToQuestionDTO(ShortAnswerQuestion question);

    QuestionDTO questionToQuestionDTO(MultipleChoiceQuestion question);

    QuestionDTO questionToQuestionDTO(Question question);

    @Mapping(target = "userId", expression = "java(userAnswer.getUser().getId())")
    UserAnswerDTO userAnswerToUserAnswerDTO(UserAnswer userAnswer);

    /**
     * POST
     */
    //    Session sessionDTOToSession(SessionDTO sessionDTO);

    User userDTOToUser(UserPostDTO userDTO);

    //    Kahoot kahootDTOToKahoot(KahootDTO kahootDTO);
    Organizer organizerDTOToOrganizer(OrganizerDTO organizerDTO);
//    Question shortAnswerQuestionDTOToQuestion(ShortAnswerQuestionDTO questionDTO);
//    Question multipleChoiceQuestionDTOToQuestion(MultipleChoiceQuestionDTO questionDTO);
}
