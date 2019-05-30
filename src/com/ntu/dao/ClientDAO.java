package com.ntu.dao;

import com.ntu.domain.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> getAllClientsByDealershipId(int id);

    boolean insertClient(Client client);
    boolean updateClient(Client client);
    boolean deleteCLient(int id);
}
