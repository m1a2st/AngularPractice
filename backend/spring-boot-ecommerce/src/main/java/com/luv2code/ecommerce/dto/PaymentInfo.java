package com.luv2code.ecommerce.dto;

import lombok.Data;

/**
 * @Author
 * @Date
 * @Version
 * @Description
 */
@Data
public class PaymentInfo {

    private Integer amount;
    private String currency;
    private String receiptEmail;
}
