package app.adib.DataModel;

public class TranslateStory {


    private String id, title, author_name, content, author_image;

    public TranslateStory(String id, String title, String author_name, String content, String author_image) {
        this.id = id;
        this.title = title;
        this.author_name = author_name;
        this.content = content;
        this.author_image = author_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor_image() {
        return author_image;
    }

    public void setAuthor_image(String author_image) {
        this.author_image = author_image;
    }
}
