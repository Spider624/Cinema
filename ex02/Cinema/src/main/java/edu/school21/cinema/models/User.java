package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class User extends AbstractModel {

    /** Аватар пользователя */
    @Nullable
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_file_id", unique = true)
    private FileInfo avatarFile;
}
