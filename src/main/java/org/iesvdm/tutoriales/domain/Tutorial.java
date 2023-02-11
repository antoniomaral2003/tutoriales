package org.iesvdm.tutoriales.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//Lombok

@Entity
@Table(
        name = "tutorials" ,
        schema = "bbdd_tutoriales",
        indexes = {@Index(name = "title_index", columnList = "title",unique = false)}
)
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_tutorial")
    private long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    private Date publicationDate;

    @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments = new ArrayList<>();

}
