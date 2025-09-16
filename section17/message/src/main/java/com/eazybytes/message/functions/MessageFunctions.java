package com.eazybytes.message.functions;

import com.eazybytes.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * All the functions that is written in this class, they are going to handle the messaging
 * to the end user
 */
@Configuration
public class MessageFunctions {
    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    /**
     * The 1st AccountsMsgDto is input,
     * The 2nd AccountsMsgDto is output
     *
     * Inside the code block, we will write a lambda method which is going
     * to act as an implementation logic for our FunctionalInterface of type Function
     */
    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
          log.info("Sending email with the details: " + accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            log.info("Sending sms with the details: " + accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }
}
