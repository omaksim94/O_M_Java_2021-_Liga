package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.model.DTO.MessageDTO;
import com.socialnetwork.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping(path = "/{personId}")
    public Page<MessageDTO> getDialogue(@PathVariable UUID personId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){
        return messageService.getDialogue(personId, PageRequest.of(page, size));
    }

    @PostMapping(path = "/{receiverId}")
    public void sendMessage(@PathVariable UUID receiverId,
                            @RequestBody String text){
        messageService.sendMessage(receiverId, text);
    }

    @PutMapping(path ="/{messageId}")
    public void editMessage(@PathVariable UUID messageId,
                            @RequestBody String newText){
        messageService.editMessage(messageId, newText);
    }

    @DeleteMapping(path = "/{messageId}")
    public void deleteMessage(@PathVariable UUID messageId){
        messageService.deleteMessage(messageId);
    }
}
