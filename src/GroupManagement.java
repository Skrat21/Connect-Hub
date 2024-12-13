public class GroupManagement {
    public static Group createGroup(String creatorId,String groupName, String groupBio, String groupImagePath){
        Group group = new Group(creatorId,groupName,groupBio,groupImagePath);
        GroupDatabase.storeGroup(group);
        return group;
    }

    public static void requestJoinGroup(User user, String name){
        Group group = GroupDatabase.getGroupFromName(name);
        group.addRequest(user.getUserId());
    }

    public void approveJoinGroup(String user, Group group){
        group.removeRequest(user);
        group.addMember(user);
    }

    public static void promoteMember(String member, Group group){
        group.addAdmin(member);
    }

    public static void leaveGroup(String userId, String groupName){
        Group group = GroupDatabase.getGroupFromName(groupName);
        group.removeMember(userId);
    }
}
