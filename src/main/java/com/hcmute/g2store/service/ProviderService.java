package com.hcmute.g2store.service;

import com.hcmute.g2store.entity.Provider;

import java.util.List;

public interface ProviderService {
    Provider addProvider(Provider provider);
    Provider delProvider(Integer id);
    Provider updateProvider(Integer id, String name, String phoneNo, String address);
    List<Provider> getAllProviders();
    Provider getProviderById(Integer id);
}
