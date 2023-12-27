package com.hcmute.g2store.controller;
import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.entity.Provider;
import com.hcmute.g2store.service.ProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProviderControllerTest {

    @Mock
    private ProviderService providerService;

    @InjectMocks
    private ProviderController providerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllEnabledProviders() {
        List<Provider> providers = Arrays.asList(new Provider(), new Provider());
        when(providerService.getAllEnabledProviders()).thenReturn(providers);
        ResponseEntity<List<Provider>> responseEntity = providerController.getAllEnabledProviders();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(providers, responseEntity.getBody());
        verify(providerService, times(1)).getAllEnabledProviders();
    }

    @Test
    void getProviderById() {
        int providerId = 1;
        Provider provider = new Provider();
        when(providerService.getProviderById(providerId)).thenReturn(provider);
        ResponseEntity<Provider> responseEntity = providerController.getProviderById(providerId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(provider, responseEntity.getBody());
        verify(providerService, times(1)).getProviderById(providerId);
    }

    @Test
    void getAllProviders() {
        List<Provider> providers = Arrays.asList(new Provider(), new Provider());
        when(providerService.getAllProviders()).thenReturn(providers);
        ResponseEntity<List<Provider>> responseEntity = providerController.getAllProviders();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(providers, responseEntity.getBody());
        verify(providerService, times(1)).getAllProviders();
    }

    @Test
    void addProvider() {
        Provider providerToAdd = new Provider();
        when(providerService.addProvider(providerToAdd)).thenReturn(providerToAdd);
        ResponseEntity<Provider> responseEntity = providerController.addProvider(providerToAdd);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(providerToAdd, responseEntity.getBody());
        verify(providerService, times(1)).addProvider(providerToAdd);
    }

    @Test
    void updateProvider() {
        Provider providerToUpdate = new Provider();
        when(providerService.updateProvider(providerToUpdate)).thenReturn(providerToUpdate);
        ResponseEntity<?> responseEntity = providerController.updateProvider(providerToUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(providerService, times(1)).updateProvider(providerToUpdate);
    }

    @Test
    void deleteProvider() {
        int providerId = 1;
        Provider mockProvider = Provider.builder()
                .id(providerId)
                .name("ProviderName")
                .build();
        when(providerService.delProvider(providerId)).thenReturn(mockProvider);
        ResponseEntity<?> responseEntity = providerController.deleteProvider(providerId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(providerService, times(1)).delProvider(providerId);
    }
}
