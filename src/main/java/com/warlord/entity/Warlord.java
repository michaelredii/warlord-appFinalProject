package com.warlord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "findAllWarlords", query = "select w from Warlord w" )
})
public class Warlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String nickname;
    private String heroType;

    public Warlord(String name, String nickname, String heroType){
        this.name = name;
        this.nickname = nickname;
        this.heroType = heroType;
    }
}
