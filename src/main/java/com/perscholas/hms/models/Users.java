package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id //primary key
    long userId;
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    long roleId;
}
