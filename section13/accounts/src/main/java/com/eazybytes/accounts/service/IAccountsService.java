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

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return boolean indicating if the delete of Account details is successful or not
     * */
    boolean deleteAccount(String mobileNumber);

    /**
     *
     * @param accountNumber - Account number
     * @return boolean indicating if the account number is updated
     * */
    boolean updateCommunicationStatus(Long accountNumber);
}
