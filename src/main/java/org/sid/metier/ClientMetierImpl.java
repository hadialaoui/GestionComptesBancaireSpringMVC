package org.sid.metier;

import javax.transaction.Transactional;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientMetierImpl implements IClientMetier {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CompteRepository compteRepository;
	
	@Override
	public Client consulterClient(Long codeClt) {
		
		return clientRepository.findOne(codeClt);
	}

	@Override
	public void supprimerClient(Long codeClt) {
		clientRepository.delete(codeClt);
	}

	@Override
	public Client ajouter(Client c) {
		return clientRepository.save(c);
	}

	@Override
	public Page<Compte> listComptes(Long codeClt,int page, int size) {	
		return compteRepository.listComptes(codeClt, new PageRequest(page, size)) ;
	}

	@Override
	public Page<Client> listClients(String motCle, int page, int size) {	
		return clientRepository.listClients(motCle, new PageRequest(page, size));
	}

}
