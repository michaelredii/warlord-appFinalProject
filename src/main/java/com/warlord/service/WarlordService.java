package com.warlord.service;

import com.warlord.entity.Warlord;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class WarlordService {
    @PersistenceContext(unitName = "warlordPersistenceUnit")
    private EntityManager manager;

    public List<Warlord> getAll(){
        return manager.createNamedQuery("findAllWarlords", Warlord.class).getResultList();
    }

    public Warlord findById(long id){
        return manager.find(Warlord.class, id);
    }

    @Transactional
    public void update(Warlord warlord){
        manager.getTransaction().begin();
        manager.merge(warlord);
        manager.getTransaction().commit();
    }

    @Transactional
    public void create(Warlord warlord){
        manager.getTransaction().begin();
        manager.persist(warlord);
        manager.getTransaction().commit();
    }

    @Transactional
    public void delete(Warlord warlord){
        manager.getTransaction().begin();
        if (!manager.contains(warlord)){
            warlord = manager.merge(warlord);
        }

        manager.remove(warlord);
        manager.getTransaction().commit();
    }
}
