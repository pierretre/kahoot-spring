package com.mapper;

import com.domain.*;
import com.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    SessionDTO sessionToSessionDTO(Session session);
    UserDTO userToUserDTO(User user);
    KahootDTO kahootToKahootDTO(Kahoot kahoot);
    OrganizerDTO organizerToOrganizerDTO(Organizer organizer);
    QuestionDTO shortAnswerQuestionToQuestionDTO(ShortAnswerQuestion question);
    QuestionDTO multipleChoiceQuestionToQuestionDTO(MultipleChoiceQuestion question);

    //    Session sessionDTOToSession(SessionDTO sessionDTO);
//    User userDTOToUser(UserDTO userDTO);
//    Kahoot kahootDTOToKahoot(KahootDTO kahootDTO);
    Organizer organizerDTOToOrganizer(OrganizerDTO organizerDTO);
//    Question shortAnswerQuestionDTOToQuestion(ShortAnswerQuestionDTO questionDTO);
//    Question multipleChoiceQuestionDTOToQuestion(MultipleChoiceQuestionDTO questionDTO);
}
