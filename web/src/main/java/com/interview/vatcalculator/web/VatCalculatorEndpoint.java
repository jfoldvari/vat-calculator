package com.interview.vatcalculator.web;

import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import com.interview.vatcalculator.web.api.VatCalculatorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public interface VatCalculatorEndpoint {

    @Operation(description = "Calculates amounts based on either net, vat or gross amount " +
            "and a valid Austrian VAT rate.",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "net", description = "Net amount.",
                            schema = @Schema(implementation = Double.class)),
                    @Parameter(in = ParameterIn.QUERY, name = "vat", description = "VAT amount.",
                            schema = @Schema(implementation = Double.class)),
                    @Parameter(in = ParameterIn.QUERY, name = "gross", description = "Gross amount.",
                            schema = @Schema(implementation = Double.class)),
                    @Parameter(in = ParameterIn.QUERY, name = "rate", description = "VAT rate.",
                            schema = @Schema(implementation = Double.class)),
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Amounts successfully retrieved.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = VatCalculatorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data in request.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = VatCalculatorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Server side problem.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = VatCalculatorResponse.class)))
            })

    @GetMapping(path = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<VatCalculatorResponse> calculateAmounts(@ParameterObject VatCalculatorRequest request);
}
