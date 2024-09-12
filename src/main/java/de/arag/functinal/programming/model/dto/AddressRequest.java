package de.arag.functinal.programming.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String zip;
}
