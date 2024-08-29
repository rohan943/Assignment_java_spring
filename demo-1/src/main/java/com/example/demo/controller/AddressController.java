package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Address;
import com.example.demo.entity.ApiResponse;
import com.example.demo.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<ApiResponse<Address>> addAddress(@RequestBody Address address) {
        Address savedAddress = addressService.addAddress(address);
        ApiResponse<Address> response = new ApiResponse<>("Address added successfully", savedAddress, true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Long id) {
        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted) {
            ApiResponse<Void> response = new ApiResponse<>("Address deleted successfully", true);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Void> response = new ApiResponse<>("Address not found", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Address>> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address updatedAddress = addressService.updateAddress(id, address);
        ApiResponse<Address> response = new ApiResponse<>("Address updated successfully", updatedAddress, true);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Address>> getAddress(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        ApiResponse<Address> response = new ApiResponse<>("Address retrieved successfully", address, true);
        return ResponseEntity.ok(response);
    }
}


