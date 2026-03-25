package services;

import core.Client;
import core.Profile;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ProfileService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Profile saveProfile(Profile profile, Client client){
        return transactionHelper.executeInTransaction(session -> {
            profile.setClient(client);
            session.persist(profile);
            return profile;
        });
    }

    public void deleteProfile(Long id){
        transactionHelper.executeInTransaction(session -> {
            Profile profileForDelete = session.get(Profile.class, id);
            session.remove(profileForDelete);
        });
    }

    public void editPhone(Long id, String newPhone){
        transactionHelper.executeInTransaction(session -> {
            Profile edit_profile = session.get(Profile.class, id);
            edit_profile.setPhone(newPhone);
        });
    }

    public void editAddress(Long id, String newAddress){
        transactionHelper.executeInTransaction(session -> {
            Profile edit_profile = session.get(Profile.class, id);
            edit_profile.setAddress(newAddress);
        });
    }

}
