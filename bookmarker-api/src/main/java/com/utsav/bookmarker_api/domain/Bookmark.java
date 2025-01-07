package com.utsav.bookmarker_api.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    private Instant createdAt;

    public Bookmark() {

    }
    // Custom constructor for easier instantiation
    public Bookmark(String title, String url, Instant createdAt) {
        this.title = title;
        this.url = url;
        this.createdAt = createdAt;
    }

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // Getter

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
