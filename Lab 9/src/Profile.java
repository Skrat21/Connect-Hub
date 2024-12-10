public class Profile {
    private String bio;
    private String profilePhoto;
    private String coverPhoto;

    public Profile(String profilePhoto, String coverPhoto, String bio) {
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }
    public String getProfilePhoto()
    {
        return profilePhoto;
    }
    public String getCoverPhoto(){
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
    public void setProfilePhoto(String profilePhoto)
    {
        this.profilePhoto=profilePhoto;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }
}
