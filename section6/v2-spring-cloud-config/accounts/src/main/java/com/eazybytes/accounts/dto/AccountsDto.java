package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold the Account information"

)
public class AccountsDto {
    @Schema(
            description = "Account number of EazyBank account",
            example = "9348304067"
    )
    @NotEmpty(message = "AccountNumber cannot be a null or empty")
    @Pattern(regexp = "^[0-9]{10}$",message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of EazyBank account",
            example = "Savings"
    )
    @NotEmpty(message = "AccountType cannot be a null or empty")
    private String accountType;

    @Schema(
            description = "EazyBank branch address",
            example = "123 New York"
    )
    @NotEmpty(message = "BranchAddress cannot be a null or empty")
    private String branchAddress;
}
