package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository addressRepository;

    public Address addAddress(Address address) {
        logger.info("Adding address: {}", address);
        Address savedAddress = addressRepository.save(address);
        logger.info("Address added successfully: {}", savedAddress);
        return savedAddress;
    }

    public void deleteAddress(Long id) {
        logger.info("Deleting address with ID: {}", id);
        addressRepository.deleteById(id);
        logger.info("Address deleted successfully with ID: {}", id);
    }

    public Address updateAddress(Long id, Address addressDetails) {
        logger.info("Updating address with ID: {} with details: {}", id, addressDetails);
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        Address updatedAddress = addressRepository.save(address);
        logger.info("Address updated successfully: {}", updatedAddress);
        return updatedAddress;
    }
    
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow();
    }
}

