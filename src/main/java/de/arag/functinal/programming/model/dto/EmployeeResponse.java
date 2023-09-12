package de.arag.functinal.programming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeResponse implements Externalizable {
    private static final long serialVersionUUI = 1L;
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String jobTitle;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(email);
        out.writeUTF(firstName);
        out.writeUTF(lastName);
        out.writeUTF(jobTitle);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        email       = in.readUTF();
        firstName   = in.readUTF();
        lastName    = in.readUTF();
        jobTitle    = in.readUTF();
    }
}
