import java.util.ArrayList;
import java.util.Random;

public class Group {
    private String groupId;
    private String creatorId;
    private ArrayList<String> adminIds;
    private ArrayList<String> memberIds;
    private ArrayList<String> requestIds;
    private String groupName;
    private String groupBio;
    private String groupImagePath;

    public Group(String creatorId,String groupName, String groupBio, String groupImagePath) {
        this.creatorId = creatorId;
        this.adminIds = new ArrayList<>();
        this.memberIds = new ArrayList<>();
        this.requestIds = new ArrayList<>();
        this.groupName = groupName;
        this.groupBio = groupBio;
        this.groupImagePath = groupImagePath;
        this.groupId = createGroupId();
    }

    private String createGroupId(){
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

    public void addMember(String memberId){
        memberIds.add(memberId);
    }

    public void removeMember(String memberId){
        memberIds.remove(memberId);
    }

    public void addAdmin(String adminId){
        adminIds.add(adminId);
    }

    public void removeAdmin(String adminId){
        adminIds.remove(adminId);
    }

    public String getCreatorId() {
        return creatorId;
    }

    public ArrayList<String> getAdminIds() {
        return adminIds;
    }

    public ArrayList<String> getMemberIds() {
        return memberIds;
    }

    public void addRequest(String requestId){
        requestIds.add(requestId);
    }

    public void removeRequest(String requestId){
        requestIds.remove(requestId);
    }

    public String getGroupId(){
        return groupId;
    }

    public String getGroupName(){
        return groupName;
    }
}
