package com.warlord;

import com.warlord.entity.Warlord;
import com.warlord.service.WarlordService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AllWarlordsView {
    private List<Warlord> warlords;

    @EJB
    private WarlordService warlordService;

    @PostConstruct
    public void init(){
        warlords = new ArrayList<>();
        warlords.addAll(warlordService.getAll());
    }

    public List<Warlord> getWarlords(){
        return warlords;
    }

    public void setWarlords(List<Warlord> warlords){
        this.warlords = warlords;
    }

    public String deleteWarlords(long id){
        warlordService.delete(warlordService.findById(id));
        return "/warlords.xhtml?faces-redirect=true";

    }

    public String redirectToEditWarlord(){

        return "/editWarlord.xhtml?faces-redirect=true";
    }




}
