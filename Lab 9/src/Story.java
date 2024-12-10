import java.io.IOException;
import java.time.LocalDateTime;

public class Story extends Content {
    private LocalDateTime expiredTime;
    private boolean available;

    public Story(String authorId,String[] imgPath, String content) throws IOException {
        super(authorId,imgPath, content);
        this.expiredTime = timestamp.plusDays(1);
    }
}
