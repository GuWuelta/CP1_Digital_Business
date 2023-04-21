package br.com.fiap.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.fiap.dao.ProfileDAO;
import br.com.fiap.model.Profile;

@Named
public class ProfileBean {
    Profile profile = new Profile();

    @Inject
    private ProfileDAO profileDao;

    private Profile selectedProfile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Transactional
    public void save() {
        if(profile.getName()!="" && profile.getEmail()!="" && profile.getPassword()!="") {
            profileDao.salvar(profile);
            this.profile = new Profile();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "As informações foram salvas com sucesso.", "INFO"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha todos os campos", "ERROR"));
        }
    }

    public List<Profile> findAll(){
        return profileDao.findAllProfiles();
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }
}
