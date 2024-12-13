import java.util.ArrayList;
import java.util.*;


public class Group {
    private String groupId;
    private String groupName;
    private String description;
    private List<String> members;

    public Group(String groupId, String groupName, String description) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.members = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String userId) {
        this.members.add(userId);
    }

    public void removeMember(String userId) {
        this.members.remove(userId);
    }
}
