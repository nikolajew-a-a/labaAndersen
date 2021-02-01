//package com.example.android.topic41.domain.util;
//
//import android.os.Parcel
//import android.os.Parcelable
//import androidx.room.*
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//
//@Entity(tableName = "articles_table", indices = [Index(value = ["description"], unique = true)])
//class Article : Parcelable {
//    @PrimaryKey(autoGenerate = true)
//    var idArticle: Long? = null
//
//    @ColumnInfo(name = "theme")
//    var theme: String? = null
//
//    @ColumnInfo(name = "time")
//    var time: Long = 0
//
//    @Embedded
//    @SerializedName("source")
//    @Expose
//    var source: Source
//        private set
//
//    @SerializedName("author")
//    @Expose
//    @ColumnInfo(name = "author")
//    var author: String?
//        private set
//
//    @SerializedName("title")
//    @Expose
//    @ColumnInfo(name = "title")
//    var title: String?
//
//    @SerializedName("description")
//    @Expose
//    @ColumnInfo(name = "description")
//    var description: String?
//        private set
//
//    @SerializedName("url")
//    @Expose
//    @ColumnInfo(name = "url")
//    var url: String?
//        private set
//
//    @SerializedName("urlToImage")
//    @Expose
//    @ColumnInfo(name = "urlToImage")
//    var urlToImage: String?
//        private set
//
//    @SerializedName("publishedAt")
//    @Expose
//    @ColumnInfo(name = "publishedAt")
//    var publishedAt: String?
//        private set
//    var content: String?
//        private set
//
//    constructor(`in`: Parcel) {
//        val data = arrayOfNulls<String>(9)
//        `in`.readStringArray(data)
//        source = Source(data[0]!!, data[1]!!)
//        author = data[2]
//        title = data[3]
//        description = data[4]
//        url = data[5]
//        urlToImage = data[6]
//        publishedAt = data[7]
//        content = data[8]
//    }
//
//    constructor(source: Source, author: String, title: String, description: String,
//                url: String, urlToImage: String, publishedAt: String, content: String) {
//        this.source = source
//        this.author = author
//        this.title = title
//        this.description = description
//        this.url = url
//        this.urlToImage = urlToImage
//        this.publishedAt = publishedAt
//        this.content = content
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeStringArray(arrayOf(source.id, source.name, author, title,
//                description, url, urlToImage, publishedAt, content))
//    }
//
//    companion object {
//        val CREATOR: Parcelable.Creator<Article?> = object : Parcelable.Creator<Article?> {
//            override fun createFromParcel(`in`: Parcel): Article? {
//                return Article(`in`)
//            }
//
//            override fun newArray(size: Int): Array<Article?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
//}
