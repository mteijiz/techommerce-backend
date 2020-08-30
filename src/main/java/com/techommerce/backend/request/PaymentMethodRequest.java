package com.techommerce.backend.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodRequest {

	private String ccNumber;
	private String ccExpMonth;
	private String ccExpYear;
	private String ccCvc;
	private String ccHolderName;
	
}
