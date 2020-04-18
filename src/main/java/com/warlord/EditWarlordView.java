package com.warlord;

import com.warlord.entity.Warlord;
import com.warlord.service.WarlordService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditWarlordView implements Serializable {

    private String name;
    private String nickname;
    private String heroType;

    @EJB
    private WarlordService warlordService;
    private transient Warlord warlordToUpdate;

    public void populateView(long warlordId){
        warlordToUpdate = warlordService.findById(warlordId);
        this.setName(warlordToUpdate.getName());
        this.setHeroType(warlordToUpdate.getHeroType());
        this.setNickname(warlordToUpdate.getNickname());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String save(){
        Warlord createdWarlord = new Warlord(name,nickname,heroType);
        if (warlordToUpdate !=null){
            createdWarlord.setId(warlordToUpdate.getId());
            warlordService.update(createdWarlord);
        } else {
            warlordService.create(createdWarlord);
        }
        nullifyFields();
        return "/warlords.xhtml?faces-redirect=true";
    }

    private void nullifyFields(){
        warlordToUpdate = null;
        this.setName(null);
        this.setNickname(null);
        this.setHeroType(null);
    }

}
