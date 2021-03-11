package com.blog.app.BlogApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@EqualsAndHashCode(exclude = "categories")

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;

    @Column(unique = true)
    private String blogTopic;
    private String blogDescription;

    /* blog can have many comments: OneToMany */
    @Getter
    @Setter
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "blog")
    @JsonManagedReference
    private List<Comment> comments=new ArrayList<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    /* Many blogs can have one user: ManyToOne */
    @ManyToOne
    @JsonBackReference
    private User user;

    /* blog has many categories OneToMany */
    @Getter
    @Setter
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "blog")
    @JsonManagedReference
    private List<Category> categories=new ArrayList<>();

    public void addCategory(Category category){
        this.categories.add(category);
    }
    public void removeCategory(Category category){
        this.categories.remove(category);
    }

}
