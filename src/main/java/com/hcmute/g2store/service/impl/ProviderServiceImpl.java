package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.Provider;
import com.hcmute.g2store.exception.ProviderException;
import com.hcmute.g2store.repository.ProviderRepo;
import com.hcmute.g2store.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepo providerRepo;

    @Override
    public Provider addProvider(Provider provider) {
        return providerRepo.save(provider);
    }

    @Override
    public Provider delProvider(Integer id) {
        Optional<Provider> provider = providerRepo.findById(id);
        if (provider.isPresent()) {
            provider.get().setEnabled(false);
            return provider.get();
        }
        throw new ProviderException("Provider with id" + id + " not found");
    }

    @Override
    @Transactional
    public Provider updateProvider(Provider updateProvider) {
        Optional<Provider> provider = providerRepo.findById(updateProvider.getId());
        if (provider.isPresent()){
            provider.get().setName(updateProvider.getName());
            provider.get().setPhoneNo(updateProvider.getPhoneNo());
            provider.get().setAddress(updateProvider.getAddress());
            return provider.get();
        }
        throw new ProviderException("Provider: " + updateProvider.getName() + " not found");
    }

    @Override
    public List<Provider> getAllProviders() {
        List<Provider> providers = providerRepo.findAll();
        if (providers.isEmpty()) throw new ProviderException("No Provider found");
        return providers;
    }


    @Override
    public Provider getProviderById(Integer id) {
        Optional<Provider> provider = providerRepo.findById(id);
        if (provider.isPresent()){
            return provider.get();
        }
        throw new ProviderException("Provider " + id + " not found");
    }

}
