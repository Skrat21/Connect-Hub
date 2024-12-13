import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotificationsDatabase {
    private static NotificationsDatabase NotificationsDatabaseInstance = null;
        public void storeNotifications(Notifications notification) {
            ArrayList<Notifications> notifications = readJsonFile();
            notifications.add(notification);
            writeJsonFile(notifications);
        }

        public Notifications getNotification(String NotificationId) {
            ArrayList<Notifications> notification = readJsonFile();
            for (Notifications notifications : notification) {
                if (notifications.getNotificationId().equals(NotificationId)) {
                    return notifications;
                }
            }
            return null;
        }

    public ArrayList<Notifications> getUserNotifications(String userId) {   //reciever id
        ArrayList<Notifications> allNotifications = readJsonFile();
        ArrayList<Notifications> userNotifications = new ArrayList<>();
        for (Notifications notification : allNotifications) {
            if (notification.getUsreId().equals(userId)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

        public ArrayList<Notifications> readJsonFile(){
            try (Reader reader = new FileReader("Notifications.json")) {
                Type listType = new TypeToken<ArrayList<Notifications>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<Notifications> notification = gson.fromJson(reader, listType);
                return (notification != null) ? notification : new ArrayList<>();
            } catch (FileNotFoundException e) {
                return new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        private void writeJsonFile(ArrayList<Notifications> notfication) {
            try (Writer writer = new FileWriter("Notifications.json")) {
                Gson gson = new Gson();
                gson.toJson(notfication, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    //singleton design pattern//
    public synchronized static NotificationsDatabase getInstance()
    {
        if(NotificationsDatabaseInstance==null)
        {
            NotificationsDatabaseInstance = new NotificationsDatabase();
        }
        return NotificationsDatabaseInstance;
    }
    private NotificationsDatabase() {

    }


    }


