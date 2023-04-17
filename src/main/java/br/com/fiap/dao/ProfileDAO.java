package br.com.fiap.dao;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.com.fiap.model.Profile;

@Named
@ViewScoped
public class ProfileDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public ProfileDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void salvar(Profile profile) {
        entityManager.merge(profile);
    }

    public Profile buscarPorId(Long id) {
        return entityManager.find(Profile.class, id);
    }

    public List<Profile> findAllProfiles() {
        @SuppressWarnings("unchecked")
        TypedQuery<Profile> query = (TypedQuery<Profile>) entityManager.createQuery("SELECT e FROM Profile e");
        return query.getResultList();
    }

    public List<Profile> findAllProfiles2() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> rootEntry = cq.from(Profile.class);
        CriteriaQuery<Profile> all = cq.select(rootEntry);
        TypedQuery<Profile> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}