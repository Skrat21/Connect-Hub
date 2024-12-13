import java.time.LocalDateTime;
import java.util.Random;

public class Notifications {
   private String message;
    private String usreId;
    private LocalDateTime date;
    private String notificationId;
    public Notifications(String message, String usreId, LocalDateTime date) {
        this.message = message;
        this.usreId = usreId;
        this.date = date;
    }
    public String getMessage() {
        return message;
    }
    public String getUsreId() {
        return usreId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public String getNotficationId() {
        return notificationId;
    }
    public String createNotificationId(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 9;
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }
}
