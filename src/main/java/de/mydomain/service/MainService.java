package de.mydomain.service;

import de.mydomain.model.Product;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final KieContainer kieContainer;

    @Autowired
    public MainService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Product getProductDiscount(Product product) {
        // get the stateful session
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        kieSession.insert(product);
        kieSession.fireAllRules();

        kieSession.dispose();
        return product;
    }
}