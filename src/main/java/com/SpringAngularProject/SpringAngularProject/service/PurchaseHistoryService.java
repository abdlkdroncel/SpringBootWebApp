package com.SpringAngularProject.SpringAngularProject.service;

import com.SpringAngularProject.SpringAngularProject.model.PurchaseHistory;
import com.SpringAngularProject.SpringAngularProject.repository.IPurchaseHistoryRepository;
import com.SpringAngularProject.SpringAngularProject.repository.projection.IPurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService {

    @Autowired
    private IPurchaseHistoryRepository iPurchaseHistoryRepository;

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory){
        purchaseHistory.setPurchaseTime(LocalDateTime.now());

        return iPurchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public List<IPurchaseItem> findPurchasedItemOfUser(Long userId){
        return iPurchaseHistoryRepository.findAllPurchaseOfUser(userId);
    }

}
