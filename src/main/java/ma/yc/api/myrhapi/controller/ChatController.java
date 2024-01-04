package ma.yc.api.myrhapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @GetMapping("/socket-chat")
    public String chatPage() {
        return "chat.html";
    }
}