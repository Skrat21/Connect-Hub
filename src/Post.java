import java.io.IOException;
import java.time.LocalDateTime;

public class Post extends Content{

    public Post(String authorId, String[] imgPath, LocalDateTime timeStamp, String content) throws IOException {
        super(authorId, imgPath,timeStamp,content);
    }
}
