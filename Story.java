import java.util.Calendar;
import java.util.Date;

public class Story extends Content {
    private Date expiredTime;
    private boolean available;

    public Story(String contentId, String authorId, String content, String imgPath) {
        super(contentId, authorId, content, imgPath);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.HOUR, 24);
        this.expiredTime = calendar.getTime();
        Date currentTime = new Date();
        this.available = currentTime.before(this.expiredTime);
    }

    public Date getExpiredTime() {
        return (expiredTime);
    }
    public boolean IsAvailable() {
        return available;
    }
}
