public class GroupManagement {
    public Group createGroup(String creatorId,String groupName, String groupBio, String groupImagePath){
        Group group = new Group(creatorId,groupName,groupBio,groupImagePath);
        GroupDatabase.storeGroup(group);
        return group;
    }

    public void requestJoinGroup(User user, Group group){
        group.addRequest(user.getUserId());
    }

    public void approveJoinGroup(String user, Group group){
        group.removeRequest(user);
        group.addMember(user);
    }

    public void promoteMember(String member, Group group){
        group.addAdmin(member);
    }
}
