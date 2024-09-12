package de.arag.functinal.programming.model.dto.immutable;

import de.arag.functinal.programming.model.dto.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public final class  Student {
    private final String name;
    private final int age;
    private final AddressRequest address;
    private final List<String> contacts;


    public List<String> getContacts() {
        return new ArrayList<>(contacts);
    }

    public AddressRequest getAddress() {
        return AddressRequest.builder()
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .street(address.getStreet())
                .build();
    }

}
