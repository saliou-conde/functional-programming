package de.arag.functinal.programming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public abstract class IEmployee {
    private String firstname;
    private String lastname;
    private String employeeId;

    abstract public void set();

    public static final void get() {

    }

    public abstract void display(String name);

    public void display(String firstname, String lastname) {
        log.info(firstname+" "+lastname);
    }

}
