package bb.love_letter;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChatController {
    public ChatModel model;
    private BufferedWriter bufferedWriter;

    public ChatController(ChatModel chatModel) {
        this.model= chatModel;
        try (FileWriter fileWriter = new FileWriter("tmp.txt")) {
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addChatMessage(ChatMessage chatMessage) {
        this.model.addChatMessage(chatMessage);
    }
    public void addUser(User user){
        this.model.addUser(user);
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}




