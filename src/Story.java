import java.io.IOException;
import java.time.LocalDateTime;

public class Story extends Content {
    private LocalDateTime expiredTime;


    public Story(String authorId, String[] imgPath, LocalDateTime timestamp,LocalDateTime expiredTime, String description, String contentId) throws IOException {
        super(authorId, imgPath, timestamp, description, contentId);
        this.expiredTime = expiredTime;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }
}