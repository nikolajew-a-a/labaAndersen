package com.example.android.topic41.domain.util;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "articles_table", indices = {@Index(value = {"description"}, unique = true)})
public class Article implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Long idArticle;

    @ColumnInfo(name = "theme")
    private String theme;

    @ColumnInfo(name = "time")
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Embedded
    @SerializedName("source")
    @Expose
    private Source source;

    @SerializedName("author")
    @Expose
    @ColumnInfo(name = "author")
    private String author;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    private String url;

    @SerializedName("urlToImage")
    @Expose
    @ColumnInfo(name = "urlToImage")
    private String urlToImage;

    @SerializedName("publishedAt")
    @Expose
    @ColumnInfo(name = "publishedAt")
    private String publishedAt;

    private String content;

    public Article(@NonNull Parcel in) {
        String[] data = new String[9];
        in.readStringArray(data);
        source = new Source(data[0], data[1]);
        author = data[2];
        title = data[3];
        description = data[4];
        url = data[5];
        urlToImage = data[6];
        publishedAt = data[7];
        content = data[8];
    }

    public Article(@NonNull Source source, @NonNull String author, @NonNull String title, @NonNull String description,
                   @NonNull String url, @NonNull String urlToImage, @NonNull String publishedAt, @NonNull String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public Long getIdArticle() {
        return idArticle;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeStringArray(new String[] {source.getId(), source.getName(), author, title,
                                            description, url, urlToImage, publishedAt, content});
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(@NonNull Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
