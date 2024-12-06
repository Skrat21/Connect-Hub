import java.io.IOException;

public class Post extends Content{

    public Post(String authorId, String[] imgPath, String content) throws IOException {
        super(authorId, imgPath, content);
    }
}
