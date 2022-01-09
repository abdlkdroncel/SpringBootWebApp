package com.SpringAngularProject.SpringAngularProject.service;

import com.SpringAngularProject.SpringAngularProject.model.PurchaseHistory;
import com.SpringAngularProject.SpringAngularProject.repository.projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {
    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

    List<IPurchaseItem> findPurchasedItemOfUser(Long userId);
}
