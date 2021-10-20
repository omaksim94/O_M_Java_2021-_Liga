package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.DTO.MessageDTO;
import com.socialnetwork.demo.model.Dialogue;
import com.socialnetwork.demo.model.Message;
import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.repository.DialogueRepository;
import com.socialnetwork.demo.repository.MessageRepository;
import com.socialnetwork.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final DialogueRepository dialogueRepository;
    private final PersonRepository personRepository;

    public Page<MessageDTO> getDialogue(UUID personId, Pageable pageable) {

        UUID senderId = getSender().getId();
        String dialogueId = getDialogId(personId, senderId);

        return messageRepository.findByDialogueId(dialogueId, pageable)
                .map(MessageDTO::new);
    }

    @Transactional
    public void sendMessage(UUID receiverId, String text) {
        Person sender = getSender();
        UUID senderId = sender.getId();
        String dialogueId = getDialogId(senderId, receiverId);
        Person receiver = personRepository.getById(receiverId);
        Dialogue dialogue = dialogueRepository.findById(dialogueId)
                .orElseGet(() -> new Dialogue(dialogueId, sender, receiver));

        Message newMessage = new Message(
                LocalDateTime.now(),
                text,
                sender,
                receiver,
                dialogue
        );
        messageRepository.save(newMessage);
    }

    @Transactional
    public void editMessage(UUID messageId, String newText) {
        Message messageToEdit = messageRepository.getById(messageId);
        messageToEdit.setText(newText);
        messageRepository.save(messageToEdit);
    }

    @Transactional
    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }

    private Person getSender() {
        String senderUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
    }

    private String getDialogId(UUID person1, UUID person2) {
        return Stream.of(person1, person2)
                .sorted()
                .map(UUID::toString)
                .collect(Collectors.joining(":"));
    }

}
