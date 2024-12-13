

public class GroupManagement {
    // 1. Create a private static variable to hold the single instance of the class
    private static GroupManagement groupManagementInstance = null;

    // 2. Private constructor to prevent external instantiation
    private GroupManagement() {
    }

    // 3. Public method to provide access to the single instance of the class
    public static synchronized GroupManagement getInstance() {
        if (groupManagementInstance == null) {
            groupManagementInstance = new GroupManagement();
        }
        return groupManagementInstance;
    }

    // 4. Method to join a group
    public boolean joinGroup(String userId, String groupId) {
        Group group = GroupDatabase.getInstance().getGroupById(groupId);
        if (group == null) {
            return false; // Group not found
        }

        group.addMember(userId); // Add the user to the group
        return true;
    }

    // 5. Method to leave a group
    public boolean leaveGroup(String userId, String groupId) {
        Group group = GroupDatabase.getInstance().getGroupById(groupId);
        if (group == null) {
            return false; // Group not found
        }

        group.removeMember(userId); // Remove the user from the group
        return true;
    }
}
