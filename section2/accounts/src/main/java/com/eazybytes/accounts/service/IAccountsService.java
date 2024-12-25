package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {
    // Java doc method comment
    /**
     *
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
