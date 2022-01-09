package com.SpringAngularProject.SpringAngularProject.controller;

import com.SpringAngularProject.SpringAngularProject.model.PurchaseHistory;
import com.SpringAngularProject.SpringAngularProject.security.UserPrincipal;
import com.SpringAngularProject.SpringAngularProject.service.IPurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase-histroy")
public class PurchaseHistoryController {

    @Autowired
    private IPurchaseHistoryService purchaseHistoryService;

    @PostMapping
    public ResponseEntity<?> savePurchaseHistory(@RequestBody PurchaseHistory purchaseHistory){
        return new ResponseEntity<>(purchaseHistoryService.savePurchaseHistory(purchaseHistory), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchaseOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(purchaseHistoryService.findPurchasedItemOfUser(userPrincipal.getId()));
    }


}
