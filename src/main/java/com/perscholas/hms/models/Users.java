package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    long userId;
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    long roleId;
}
